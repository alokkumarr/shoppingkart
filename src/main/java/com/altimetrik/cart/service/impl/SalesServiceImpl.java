package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.response.ProductItem;
import com.altimetrik.cart.model.response.Receipt;
import com.altimetrik.cart.repository.AddItemCartRepository;
import com.altimetrik.cart.repository.entity.*;
import com.altimetrik.cart.service.CustomerService;
import com.altimetrik.cart.service.SalesService;
import com.altimetrik.cart.service.TaxDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SalesServiceImpl.class);

  private static final DecimalFormat df = new DecimalFormat("####0.00");
  @Autowired
  private CustomerService customerService;
  @Autowired
  private AddItemCartRepository cartRepository;
  @Autowired
  private TaxDetailService taxDetailService;

  /**
   * Calculate the taxes
   *
   * @param basePrice
   * @param valueOf
   * @return
   */
  private static Double calculateTax(Double basePrice, Double valueOf) {
    if (basePrice > 0) {
      Double taxValue =  (basePrice * valueOf) / 100;
      return Double.valueOf(df.format(taxValue));
    } else {
      return 0.0;
    }
  }

  @Override
  public Receipt checkOut(Long customerId) {

    Receipt receipt = new Receipt();
    SalesDetail salesDetail = new SalesDetail();
    if (customerId > 0) {
      Customer customer = customerService.getCustomerById(customerId);
      if (customer != null && customer.getId() > 0) {
        Address add = customer.getCustomerAddress().stream()
            .filter(address -> "Billing".equalsIgnoreCase(address.getType())
                || "Service".equalsIgnoreCase(address.getType())).findAny().get();

        TaxDetails[] taxDetail = {new TaxDetails()};
        if (add.getState() != null && add.getCountry() != null) {
          taxDetail[0] = taxDetailService.findByRegion(add.getState(), add.getCountry());
          LOGGER.info("Tax details from for the region : {} ", taxDetail);
        }

        List<ProductItem> productItems = new ArrayList<>();
        List<AddItemCart> cartItems = cartRepository.findItemByCustomerId(customerId);
        if (cartItems != null && !cartItems.isEmpty()) {
          cartItems.stream().forEach(addItemCart -> {
            ProductItem productItem = new ProductItem();
            productItem.setName(addItemCart.getName());
            productItem.setCategory(addItemCart.getProductType());
            productItem.setPrice(addItemCart.getPrice());
            productItem.setDescription(addItemCart.getDescription());
            TaxDetails taxDetails = taxDetail[0];
            if (taxDetails != null) {
              Double basePrice = Double.valueOf(addItemCart.getPrice());
              Double salesTax = calculateTax(basePrice, Double.valueOf(taxDetails.getSalesTax()));
              Double vat = calculateTax(basePrice, Double.valueOf(taxDetails.getVat()));
              Double dutyTax = calculateTax(basePrice, Double.valueOf(taxDetails.getImportDuty()));

              // add the tax details for each item and total price
              productItem.setTax(salesTax);
              productItem.setVat(vat);
              productItem.setDuties(dutyTax);
              Double totalPrice = basePrice + vat + salesTax + dutyTax;
              productItem.setTotalPrice(Double.valueOf(df.format(totalPrice)));
              productItems.add(productItem);

              // add the details in sales table
              persistSalesDetails(productItem);
            }
          });
          receipt.setProductItems(productItems);
        }
      }
    }
    // send the final receipt as json format
    return receipt;
  }

  private void persistSalesDetails(ProductItem productItem) {

  }
}
