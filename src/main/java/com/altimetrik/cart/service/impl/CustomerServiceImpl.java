package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.CustomerDetails;
import com.altimetrik.cart.model.States;
import com.altimetrik.cart.repository.CustomerRepository;
import com.altimetrik.cart.repository.entity.Address;
import com.altimetrik.cart.repository.entity.Customer;
import com.altimetrik.cart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    Set<Address> addressList = new HashSet<>();
    details.getAddresses().stream().forEach(address -> {
      Address addr = new Address();
      addr.setCity(address.getCity());
      addr.setCountry(address.getCountry());
      addr.setState(States.getState(address.getState()));
      addr.setStreet(address.getStreet());
      addr.setType(address.getType().toUpperCase());
      addressList.add(addr);
    });
    customer.setCustomerAddress(addressList);
    return customerRepository.save(customer);
  }

  @Override
  public Customer getCustomerById(Long id) {
    Customer customer = new Customer();
    if (customerRepository.findById(id).isPresent()) {
      customer =  customerRepository.findById(id).get();
    }
    return customer;
  }

  @Override
  public List<Customer> getCustomers() {
    return customerRepository.findAll();
  }
}
