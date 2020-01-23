package com.altimetrik.cart.service;

import com.altimetrik.cart.model.response.Receipt;

public interface SalesService {
  Receipt checkOut(Long customerId);
}
