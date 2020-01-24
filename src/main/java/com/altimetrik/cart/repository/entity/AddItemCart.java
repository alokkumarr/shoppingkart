package com.altimetrik.cart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "ADD_TO_CART")
public class AddItemCart {


  @Id
  @Column(name = "CART_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long itemId;
  private Long customerId;
  private Integer quantity;
  private String sku;
  private String name;
  private Double price;
  private String category;
  private String imported;
  private String productType;
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
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

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImported() {
    return imported;
  }

  public void setImported(String imported) {
    this.imported = imported;
  }
}
