package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDetails {

  @JsonProperty("id")
  private Long customerId;
  @JsonProperty("name")
  private String customerName;
  @JsonProperty("email")
  private String customerEmail;
  @JsonProperty("phoneNumber")
  private Long phoneNumber;
  @JsonProperty("shippingAddress")
  private String shippingAddress;
  @JsonProperty("billingAddress")
  private String billingAddress;

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

  public String getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public String getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(String billingAddress) {
    this.billingAddress = billingAddress;
  }
}
