package com.altimetrik.cart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "TAX_DETAILS")
public class TaxDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long taxId;

  private String state;
  private String country;
  private String category;
  private Double salesTax;
  private Double vat;
  private Double importDuty;

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Long getTaxId() {
    return taxId;
  }

  public void setTaxId(Long taxId) {
    this.taxId = taxId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Double getSalesTax() {
    return salesTax;
  }

  public void setSalesTax(Double salesTax) {
    this.salesTax = salesTax;
  }

  public Double getImportDuty() {
    return importDuty;
  }

  public void setImportDuty(Double importDuty) {
    this.importDuty = importDuty;
  }

  public Double getVat() {
    return vat;
  }

  public void setVat(Double vat) {
    this.vat = vat;
  }
}
