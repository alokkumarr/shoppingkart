package com.altimetrik.cart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "DISCOUNT_DETAILS")
public class Discount {

  @Id
  @Column(name = "DISCOUNT_Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long discountId;

  private Double amount;
  private Double maxAmount;
  private Double discount;

  public Long getDiscountId() {
    return discountId;
  }

  public void setDiscountId(Long discountId) {
    this.discountId = discountId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Double getMaxAmount() {
    return maxAmount;
  }

  public void setMaxAmount(Double maxAmount) {
    this.maxAmount = maxAmount;
  }
}
