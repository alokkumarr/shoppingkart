package com.altimetrik.cart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_DETAILS")
public class Item {

  @Id
  @Column(name = "ITEM_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String sku;
  private String category;
  private String name;
  private Double price;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "ITEM_ID")
  private BookDetails bookDetails;


  public Item() {
    super();
  }

  public Item(String sku, String category, String name, Double price, String description, BookDetails bookDetails) {
    super();
    this.sku = sku;
    this.category = category;
    this.name = name;
    this.price = price;
    this.bookDetails = bookDetails;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
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

  public BookDetails getBookDetails() {
    return bookDetails;
  }

  public void setBookDetails(BookDetails bookDetails) {
    this.bookDetails = bookDetails;
  }
}
