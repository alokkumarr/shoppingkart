package com.altimetrik.cart.repository.entity;


import javax.persistence.*;

@Entity
@Table
public class SalesDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long customerId;
  private String itemId;
  private Double price;
  private Double tax;
  private Double vat;
  private Double duties;
  private Double grossAmount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getTax() {
    return tax;
  }

  public void setTax(Double tax) {
    this.tax = tax;
  }

  public Double getVat() {
    return vat;
  }

  public void setVat(Double vat) {
    this.vat = vat;
  }

  public Double getDuties() {
    return duties;
  }

  public void setDuties(Double duties) {
    this.duties = duties;
  }

  public Double getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(Double grossAmount) {
    this.grossAmount = grossAmount;
  }
}
