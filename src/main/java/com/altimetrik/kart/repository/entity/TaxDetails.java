package com.altimetrik.kart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "TAX_DETAILS")
public class TaxDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long taxId;

  private String country;
  private String category;
  private Double salesTax;
  private String importedTax;

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

  public String getImportedTax() {
    return importedTax;
  }

  public void setImportedTax(String importedTax) {
    this.importedTax = importedTax;
  }
}
