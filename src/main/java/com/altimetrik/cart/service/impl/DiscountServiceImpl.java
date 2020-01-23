package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.DiscountDetails;
import com.altimetrik.cart.repository.DiscountRepository;
import com.altimetrik.cart.repository.entity.Discount;
import com.altimetrik.cart.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

  @Autowired
  private DiscountRepository discountRepository;

  @Override
  public List<Discount> addDiscountDetails(DiscountDetails details) {
    List<Discount> list = new ArrayList<>();
    details.getDiscounts().stream().forEach(discount -> {
      Discount dis = new Discount();
      dis.setAmount(discount.getDiscountAmount());
      dis.setMaxAmount(discount.getMaxAmount());
      dis.setDiscount(discount.getDiscountPercentage());
      list.add(dis);
    });
    return discountRepository.saveAll(list);
  }

  @Override
  public List<Discount> fetchAllDiscount() {
    return discountRepository.findAll();
  }
}
