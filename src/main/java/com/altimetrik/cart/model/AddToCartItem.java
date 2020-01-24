package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddToCartItem {

  @JsonProperty("itemId")
  private String itemId;
  @JsonProperty("qty")
  private String qty;
  @JsonProperty("sku")
  private String sku;

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getQty() {
    return qty;
  }

  public void setQty(String qty) {
    this.qty = qty;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }
}
