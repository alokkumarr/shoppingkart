package com.altimetrik.cart.service;

import com.altimetrik.cart.model.CustomerDetails;
import com.altimetrik.cart.repository.entity.Customer;

import java.util.List;

public interface CustomerService {
  List<Customer> getCustomers();
  Customer getCustomerById(Long id);
  Customer addCustomer(CustomerDetails details);
}