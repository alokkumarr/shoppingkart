package com.altimetrik.cart.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRef {
  @JsonProperty("id")
  private String customerId;

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
}
