package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DiscountDetails {

  @JsonProperty("discounts")
  private List<Discount> discounts;

  public List<Discount> getDiscounts() {
    return discounts;
  }

  public void setDiscounts(List<Discount> discounts) {
    this.discounts = discounts;
  }
}
