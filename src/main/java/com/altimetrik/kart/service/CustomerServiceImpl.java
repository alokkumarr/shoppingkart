package com.altimetrik.kart.service;

import com.altimetrik.kart.entity.Customer;
import com.altimetrik.kart.model.CustomerDetails;
import com.altimetrik.kart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Customer addCustomer(CustomerDetails details) {
    Customer customer = new Customer();
    customer.setEmail(details.getCustomerEmail());
    customer.setName(details.getCustomerName());
    customer.setPhoneNumber(details.getPhoneNumber());
    customer.setBillAddress(details.getBillingAddress());
    customer.setShipAddress(details.getShippingAddress());
    return customerRepository.save(customer);
  }

  @Override
  public Object getCustomerById(Long id) {
    return customerRepository.findById(id);
  }

  @Override
  public List<Customer> getCustomers() {
    return customerRepository.findAll();
  }
}
