package com.altimetrik.cart.repository.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {

  @Id
  @Column(name = "CUST_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private Long phoneNumber;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "CUST_ID")
  private Set<Address> customerAddress;

  public Customer() {
    super();
  }

  public Customer(String name, String email, Long phoneNumber, Set<Address> customerAddress) {
    super();
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.customerAddress = customerAddress;
  }

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

  public Set<Address> getCustomerAddress() {
    return customerAddress;
  }

  public void setCustomerAddress(Set<Address> customerAddress) {
    this.customerAddress = customerAddress;
  }
}