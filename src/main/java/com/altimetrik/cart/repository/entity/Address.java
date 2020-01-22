package com.altimetrik.cart.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long addrId;

  @Column(name = "TYPE")
  private String type;
  @Column(name = "STREET")
  private String street;
  @Column(name = "CITY")
  private String city;
  @Column(name = "STATE")
  private String state;
  @Column(name = "COUNTRY")
  private String country;

  public Address() {
    super();
  }

  public Address(Long addrId, String type, String street, String city, String state, String country) {
    super();
    this.addrId=addrId;
    this.type = type;
    this.street = street;
    this.city = city;
    this.state = state;
    this.country = country;
  }

  public Long getAddrId() {
    return addrId;
  }

  public void setAddrId(Long addrId) {
    this.addrId = addrId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
