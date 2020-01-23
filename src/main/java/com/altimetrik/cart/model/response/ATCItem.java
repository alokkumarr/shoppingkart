package com.altimetrik.cart.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ATCItem {
  @JsonProperty("itemId")
  private Long itemId;
  @JsonProperty("qty")
  private Integer qty;
  @JsonProperty("sku")
  private String sku;
  @JsonProperty("price")
  private Double price;
  @JsonProperty("name")
  private String name;
  @JsonProperty("productType")
  private String productType;

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
