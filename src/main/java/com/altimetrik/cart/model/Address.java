package com.altimetrik.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

  @JsonProperty("addrId")
  private int addrId;

  @JsonProperty("type")
  private String type;
  @JsonProperty("street")
  private String street;
  @JsonProperty("city")
  private String city;
  @JsonProperty("state")
  private String state;
  @JsonProperty("country")
  private String country;

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
}
