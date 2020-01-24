package com.altimetrik.cart.service;

import com.altimetrik.cart.model.TaxDetail;
import com.altimetrik.cart.repository.entity.TaxDetails;

import java.util.List;

public interface TaxDetailService {
  List<TaxDetails> getAllTaxDetails();
  TaxDetails addTaxDetails(TaxDetail detail);
  TaxDetails findByRegion(String state, String country);
}
