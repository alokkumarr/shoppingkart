package com.altimetrik.cart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_DETAILS")
public class BookDetails {

  @Id
  @Column(name = "BOOK_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String publisher;
  private String imported;
  private String language;
  private String author;
  private String year;
  private String type;
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
