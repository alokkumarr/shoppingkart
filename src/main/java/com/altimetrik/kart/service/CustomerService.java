package com.altimetrik.kart.service;

import com.altimetrik.kart.entity.Customer;
import com.altimetrik.kart.model.CustomerDetails;

import java.util.List;

public interface CustomerService {
  List<Customer> getCustomers();
  Object getCustomerById(Long id);
  Customer addCustomer(CustomerDetails details);
}