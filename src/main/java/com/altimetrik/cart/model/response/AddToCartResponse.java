package com.altimetrik.cart.model.response;

import com.altimetrik.cart.repository.entity.AddItemCart;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddToCartResponse {

  @JsonProperty("cartDetails")
  private List<AddItemCart> cartResponse;

  @JsonProperty("message")
  private String message;

  public List<AddItemCart> getCartResponse() {
    return cartResponse;
  }

  public void setCartResponse(List<AddItemCart> cartResponse) {
    this.cartResponse = cartResponse;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
