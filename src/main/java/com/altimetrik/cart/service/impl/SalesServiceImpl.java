package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.response.CheckOutResponse;
import com.altimetrik.cart.model.response.ProductItem;
import com.altimetrik.cart.model.response.Receipt;
import com.altimetrik.cart.repository.AddItemCartRepository;
import com.altimetrik.cart.repository.entity.*;
import com.altimetrik.cart.service.CustomerService;
import com.altimetrik.cart.service.DiscountService;
import com.altimetrik.cart.service.SalesService;
import com.altimetrik.cart.service.TaxDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SalesServiceImpl.class);
  private static final DecimalFormat df = new DecimalFormat("####0.00");

  @Autowired
  private DiscountService discountService;
  @Autowired
  private CustomerService customerService;
  @Autowired
  private TaxDetailService taxDetailService;
  @Autowired
  private AddItemCartRepository cartRepository;

  /**
   * Calculate the taxes
   *
   * @param basePrice
   * @param valueOf
   * @return
   */
  private static Double calculateDiscountOrTaxPrice(Double basePrice, Double valueOf) {
    if (basePrice > 0) {
      Double taxValue = (basePrice * valueOf) / 100;
      return Double.valueOf(df.format(taxValue));
    } else {
      return 0.0;
    }
  }

  @Override
  public CheckOutResponse checkOut(Long customerId) {
    CheckOutResponse outResponse = new CheckOutResponse();
    Receipt receipt = new Receipt();
    if (customerId > 0) {
      Customer customer = customerService.getCustomerById(customerId);
      if (customer != null && customer.getId() != null && customer.getId() > 0) {
        Address add = customer.getCustomerAddress().stream()
            .filter(address -> "Billing".equalsIgnoreCase(address.getType())
                || "Service".equalsIgnoreCase(address.getType())).findAny().get();

        TaxDetails[] taxDetail = {new TaxDetails()};
        if (add.getState() != null && add.getCountry() != null) {
          taxDetail[0] = taxDetailService.findByRegion(add.getState(), add.getCountry());
          LOGGER.info("Tax details from for the region : {}", taxDetail);
        }

        List<ProductItem> productItems = new ArrayList<>();
        List<AddItemCart> cartItems = cartRepository.findItemByCustomerId(customerId);
        if (cartItems != null && !cartItems.isEmpty()) {
          Double[] mBookPrice = {0.0};
          Double[] totalBaseAmount = {0.0};
          cartItems.stream().forEach(addItemCart -> {
            ProductItem productItem = new ProductItem();
            productItem.setName(addItemCart.getName());
            productItem.setPrice(addItemCart.getPrice());
            productItem.setDescription(addItemCart.getDescription());
            productItem.setQty(addItemCart.getQuantity());
            TaxDetails taxDetails = taxDetail[0];

            if (taxDetails != null) {
              Double basePrice = Double.valueOf(addItemCart.getPrice());
              basePrice = addItemCart.getQuantity() > 0 ? basePrice * addItemCart.getQuantity() : basePrice;
              totalBaseAmount[0] += basePrice;
              Double salesTax = calculateDiscountOrTaxPrice(basePrice, Double.valueOf(taxDetails.getSalesTax()));
              Double vat = calculateDiscountOrTaxPrice(basePrice, Double.valueOf(taxDetails.getVat()));

              // check for imported book
              Double dutyTax = 0.0;
              if ("yes".equalsIgnoreCase(addItemCart.getImported())) {
                dutyTax = calculateDiscountOrTaxPrice(basePrice, taxDetails.getImportDuty());
              }

              // calculate management book price
              if ("Management".equalsIgnoreCase(addItemCart.getProductType())) {
                mBookPrice[0] += addItemCart.getPrice() * addItemCart.getQuantity();
              }

              // add the tax details for each item and total price
              productItem.setTax(salesTax);
              productItem.setVat(vat);
              productItem.setImportDuty(dutyTax);
              Double totalPrice = basePrice + vat + salesTax + dutyTax;
              productItem.setTotalPrice(Double.valueOf(df.format(totalPrice)));
              productItems.add(productItem);
            }
          });
          receipt.setProductItems(productItems);

          // Calculate the total amount and discount
          Double totalAmount = productItems.stream().collect(Collectors.summingDouble(ProductItem::getTotalPrice));
          receipt.setTotalAmount(Double.valueOf(df.format(totalAmount)));

          // calculate the discount
          List<Discount> discounts = discountService.fetchAllDiscount();
          if (discounts != null && !discounts.isEmpty()) {
            // don't include management books amount
            Double amount = totalBaseAmount[0] - mBookPrice[0];
            Optional<Discount> discountList = discounts.stream().filter(dis -> amount > dis.getAmount()
                && amount < dis.getMaxAmount()).findAny();
            if (discountList.isPresent()) {
              Double finalDiscountAmount = calculateDiscountOrTaxPrice(amount, discountList.get().getDiscount());
              receipt.setDiscount(Double.valueOf(df.format(finalDiscountAmount)));
            } else {
              receipt.setDiscount(0.0);
            }
          }
          // final amount to be paid
          double finalAmount = totalAmount - receipt.getDiscount();
          receipt.setAmountToPaid(Double.valueOf(df.format(finalAmount)));
        }
      } else {
        outResponse.setMessage("No checkout details available for this customer.");
      }
    }
    outResponse.setReceiptDetails(receipt);
    // send the final receipt as json format
    return outResponse;
  }
}
