package com.altimetrik.cart.integration;

import com.altimetrik.cart.model.AddToCartItem;
import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.request.CustomerRef;
import org.springframework.boot.web.server.LocalServerPort;

public class BaseIT {

  @LocalServerPort
  private int port;

  protected String uri(String uri) {
    return "http://localhost:" + port + uri;
  }


  protected AddToCartRequest getAddItemRequest() {
    AddToCartRequest cartRequest = new AddToCartRequest();
    AddToCartItem cartItem = new AddToCartItem();
    cartItem.setItemId("1");
    cartItem.setQty("2");
    cartItem.setSku("IB1277");
    CustomerRef ref = new CustomerRef();
    ref.setCustomerId("1");
    cartRequest.setCustomerRef(ref);
    cartRequest.setCartItem(cartItem);
    return cartRequest;
  }
}
