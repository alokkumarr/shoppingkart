package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDetails {

  @JsonProperty("id")
  private Long id;
  @JsonProperty("isbn")
  private String isbn;
  @JsonProperty("title")
  private String title;
  @JsonProperty("year")
  private String year;
  @JsonProperty("publisher")
  private String publisher;
  @JsonProperty("bestseller")
  private String bestseller;
  @JsonProperty("category")
  private String category;
  @JsonProperty("language")
  private String language;
  @JsonProperty("author")
  private String author;
  @JsonProperty("price")
  private Double price;
  @JsonProperty("description")
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public String getBestseller() {
    return bestseller;
  }

  public void setBestseller(String bestseller) {
    this.bestseller = bestseller;
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
}
