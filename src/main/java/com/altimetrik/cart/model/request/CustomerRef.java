package com.altimetrik.cart.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRef {
  @JsonProperty("id")
  private Long customerId;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }
}
