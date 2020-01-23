package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddToCartItem {

  @JsonProperty("itemId")
  private String itemId;
  @JsonProperty("qty")
  private Integer qty;
  @JsonProperty("sku")
  private String sku;

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }
}
