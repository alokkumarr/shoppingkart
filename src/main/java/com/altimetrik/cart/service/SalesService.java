package com.altimetrik.cart.service;

import com.altimetrik.cart.model.response.CheckOutResponse;

public interface SalesService {
  CheckOutResponse checkOut(Long customerId);
}
