package com.altimetrik.cart.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddToCartResponse {

  @JsonProperty("cartDetails")
  private List<ATCItem> cartResponse;

  @JsonProperty("message")
  private String message;

  public List<ATCItem> getCartResponse() {
    return cartResponse;
  }

  public void setCartResponse(List<ATCItem> cartResponse) {
    this.cartResponse = cartResponse;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
