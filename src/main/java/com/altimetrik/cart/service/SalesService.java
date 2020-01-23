package com.altimetrik.cart.service;

import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.response.AddToCartResponse;

public interface SalesService {
  AddToCartResponse addToKart(AddToCartRequest addToCartRequest);
  AddToCartResponse deleteCart(AddToCartRequest addToCartRequest);
}
