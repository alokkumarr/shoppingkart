package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.AddToCartItem;
import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.request.CustomerRef;
import com.altimetrik.cart.model.response.ATCItem;
import com.altimetrik.cart.model.response.AddToCartResponse;
import com.altimetrik.cart.repository.AddItemCartRepository;
import com.altimetrik.cart.repository.entity.AddItemCart;
import com.altimetrik.cart.repository.entity.Customer;
import com.altimetrik.cart.repository.entity.Item;
import com.altimetrik.cart.service.AddToKartService;
import com.altimetrik.cart.service.CustomerService;
import com.altimetrik.cart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddToKartServiceImpl implements AddToKartService {

  @Autowired
  private ItemService itemService;
  @Autowired
  private CustomerService customerService;
  @Autowired
  private AddItemCartRepository cartRepository;

  @Override
  public AddToCartResponse addToKart(AddToCartRequest addToCartRequest) {
    CustomerRef custRef = addToCartRequest.getCustomerRef();
    AddToCartResponse cartResponse = new AddToCartResponse();
    if (custRef.getCustomerId() > 0) {
      Customer customer = customerService.getCustomerById(custRef.getCustomerId());
      if (customer.getId() > 0) {
        List<ATCItem> atcItems = new ArrayList<>();
        AddToCartItem cartItem = addToCartRequest.getCartItem();
        if (!StringUtils.isEmpty(cartItem.getItemId())) {
          Long itemId = Long.valueOf(cartItem.getItemId());
          Item item = itemService.getItemById(itemId);
          // process add item details in table
          ATCItem atcItem = buildItem(cartItem, item);
          List<AddItemCart> list = cartRepository.findItemBySKU(item.getSku());
          if (list != null && !list.isEmpty()) {
            atcItem.setQty(cartItem.getQty());
            cartRepository.updateItemBySKU(cartItem.getQty(), list.get(0).getId());
          } else {
            AddItemCart addItemCart = getAddItemCart(customer.getId(), atcItem);
            addItemCart.setDescription(item.getBookDetails().getDescription());
            cartRepository.save(addItemCart);
          }
          atcItems.add(atcItem);
          cartResponse.setCartResponse(atcItems);
          cartResponse.setMessage("Item added in the cart.");
        } else {
          cartResponse.setMessage("No item available for the request.");
        }
      } else {
        cartResponse.setMessage("You are not authorized  to add kart.");
      }
    }
    return cartResponse;
  }

  @Override
  public AddToCartResponse deleteCart(AddToCartRequest addToCartRequest) {
    CustomerRef custRef = addToCartRequest.getCustomerRef();
    AddToCartResponse cartResponse = new AddToCartResponse();
    if (custRef.getCustomerId() > 0) {
      Customer customer = customerService.getCustomerById(custRef.getCustomerId());
      if (customer.getId() > 0) {
        AddToCartItem cartItem = addToCartRequest.getCartItem();
        if (!StringUtils.isEmpty(cartItem.getItemId())) {
          List<ATCItem> atcItems = new ArrayList<>();
          Long itemId = Long.valueOf(cartItem.getItemId());
          Item item = itemService.getItemById(itemId);
          Integer deletedCart = cartRepository.deleteItemBySKU(item.getSku());
          if (deletedCart > 0) {
            ATCItem atcItem = buildItem(cartItem, item);

            atcItems.add(atcItem);
            cartResponse.setCartResponse(atcItems);
            cartResponse.setMessage("Items cleared from Cart.");
          } else {
            cartResponse.setMessage("Cart Empty.");
          }
        }
      }
    }
    return cartResponse;
  }

  private ATCItem buildItem(AddToCartItem cartItem, Item item) {
    ATCItem atcItem = new ATCItem();
    atcItem.setItemId(item.getId());
    atcItem.setSku(item.getSku());
    atcItem.setName(item.getName());
    atcItem.setProductType(item.getCategory());
    atcItem.setQty(cartItem.getQty());
    atcItem.setPrice(item.getPrice());
    return atcItem;
  }

  private AddItemCart getAddItemCart(Long customerId, ATCItem atcItem) {
    AddItemCart addItemCart = new AddItemCart();
    addItemCart.setItemId(atcItem.getItemId());
    addItemCart.setCustomerId(customerId);
    addItemCart.setQuantity(atcItem.getQty());
    addItemCart.setName(atcItem.getName());
    addItemCart.setProductType(atcItem.getProductType());
    addItemCart.setPrice(atcItem.getPrice());
    addItemCart.setSku(atcItem.getSku());
    return addItemCart;
  }
}
