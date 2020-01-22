package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Discount {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("amount")
  private Double discountAmount;

  @JsonProperty("maxAmount")
  private Double maxAmount;

  @JsonProperty("discountPercentage")
  private Double discountPercentage;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  public Double getMaxAmount() {
    return maxAmount;
  }

  public void setMaxAmount(Double maxAmount) {
    this.maxAmount = maxAmount;
  }

  public Double getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(Double discountPercentage) {
    this.discountPercentage = discountPercentage;
  }
}
