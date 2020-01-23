package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxDetail {

  @JsonProperty("id")
  private Long id;
  @JsonProperty("country")
  private String country;
  @JsonProperty("state")
  private String state;
  @JsonProperty("vat")
  private Double vat;
  @JsonProperty("taxCategory")
  private String taxCategory;
  @JsonProperty("salesTax")
  private Double salesTax;
  @JsonProperty("importDuty")
  private Double importDuty;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getTaxCategory() {
    return taxCategory;
  }

  public void setTaxCategory(String taxCategory) {
    this.taxCategory = taxCategory;
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
