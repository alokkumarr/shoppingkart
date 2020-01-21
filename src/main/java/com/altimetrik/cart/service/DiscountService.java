package com.altimetrik.cart.service;

import com.altimetrik.cart.model.DiscountDetails;
import com.altimetrik.cart.repository.entity.Discount;

import java.util.List;

public interface DiscountService {
  List<Discount> addDiscountDetails(DiscountDetails details);
}
