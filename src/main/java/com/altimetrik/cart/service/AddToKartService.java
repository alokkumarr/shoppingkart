package com.altimetrik.cart.service;

import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.response.AddToCartResponse;
import com.altimetrik.cart.repository.entity.AddItemCart;

import java.util.List;

public interface AddToKartService {
  AddToCartResponse addToKart(AddToCartRequest addToCartRequest);
  List<AddItemCart> getCartDetails(Long customerId);
  AddToCartResponse deleteCart(AddToCartRequest addToCartRequest);
  Integer deleteCartByCustomerId(Long customerId);
}
