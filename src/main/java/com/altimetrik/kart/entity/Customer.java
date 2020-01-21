package com.altimetrik.kart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String email;
  private Long phoneNumber;
  private String billAddress;
  private String shipAddress;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getBillAddress() {
    return billAddress;
  }

  public void setBillAddress(String billAddress) {
    this.billAddress = billAddress;
  }

  public String getShipAddress() {
    return shipAddress;
  }

  public void setShipAddress(String shipAddress) {
    this.shipAddress = shipAddress;
  }
}