package com.altimetrik.cart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_DETAILS")
public class Item {

  @Id
  @Column(name = "ITEM_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String isbn;
  private String category;
  private String title;
  private String publisher;
  private String bestseller;
  private String language;
  private String author;
  private Double price;
  private String year;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
