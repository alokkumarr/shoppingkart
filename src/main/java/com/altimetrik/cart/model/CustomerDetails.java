package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomerDetails {

  @JsonProperty("id")
  private Long customerId;
  @JsonProperty("name")
  private String customerName;
  @JsonProperty("email")
  private String customerEmail;
  @JsonProperty("phoneNumber")
  private Long phoneNumber;
  @JsonProperty("addresses")
  private List<Address> addresses;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }
}
