package com.altimetrik.kart.service;

import com.altimetrik.kart.model.CustomerDetails;
import com.altimetrik.kart.repository.entity.Customer;

import java.util.List;

public interface CustomerService {
  List<Customer> getCustomers();
  Object getCustomerById(Long id);
  Customer addCustomer(CustomerDetails details);
}