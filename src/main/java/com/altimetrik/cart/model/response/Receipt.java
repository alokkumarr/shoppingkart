package com.altimetrik.cart.model.response;

import java.io.Serializable;
import java.util.List;

public class Receipt implements Serializable {

  private Double discount;
  private Double totalAmount;
  private List<ProductItem> productItems;

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public List<ProductItem> getProductItems() {
    return productItems;
  }

  public void setProductItems(List<ProductItem> productItems) {
    this.productItems = productItems;
  }
}
