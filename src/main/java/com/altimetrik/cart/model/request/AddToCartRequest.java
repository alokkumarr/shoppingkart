package com.altimetrik.cart.model.request;

import com.altimetrik.cart.model.AddToCartItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddToCartRequest {
  @JsonProperty("cartItem")
  private AddToCartItem cartItem;
  @JsonProperty("customerRef")
  private CustomerRef customerRef;

  public AddToCartItem getCartItem() {
    return cartItem;
  }

  public void setCartItem(AddToCartItem cartItem) {
    this.cartItem = cartItem;
  }

  public CustomerRef getCustomerRef() {
    return customerRef;
  }

  public void setCustomerRef(CustomerRef customerRef) {
    this.customerRef = customerRef;
  }
}
