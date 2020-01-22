package com.altimetrik.cart.repository.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int addrId;

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

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "CUST_ID", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Customer customer;
  public Address() {
    super();
  }

  public Address(String type, String street, String city, String state, String country, Customer customer) {
    super();
    this.type = type;
    this.street = street;
    this.city = city;
    this.state = state;
    this.country = country;
    this.customer = customer;
  }

  public int getAddrId() {
    return addrId;
  }

  public void setAddrId(int addrId) {
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

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
