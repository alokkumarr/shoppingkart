package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.repository.SalesDetailRepository;
import com.altimetrik.cart.service.SalesDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesDetailsImpl implements SalesDetails {
  @Autowired
  private SalesDetailRepository repository;

}
