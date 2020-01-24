package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDetails {

  @JsonProperty("id")
  private Long id;
  @JsonProperty("sku")
  private String sku;
  @JsonProperty("name")
  private String name;
  @JsonProperty("price")
  private Double price;
  @JsonProperty("description")
  private String description;
  @JsonProperty("type")
  private String type;
  @JsonProperty("year")
  private String year;
  @JsonProperty("publisher")
  private String publisher;
  @JsonProperty("imported")
  private String imported;
  @JsonProperty("category")
  private String category;
  @JsonProperty("language")
  private String language;
  @JsonProperty("author")
  private String author;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getImported() {
    return imported;
  }

  public void setImported(String imported) {
    this.imported = imported;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
