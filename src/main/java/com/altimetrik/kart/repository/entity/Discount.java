package com.altimetrik.kart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "DISCOUNT_DETAILS")
public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long discountId;

  private Double amount;
  private Long discount;

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

  public Long getDiscount() {
    return discount;
  }

  public void setDiscount(Long discount) {
    this.discount = discount;
  }
}
