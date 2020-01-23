package com.altimetrik.cart.service;

import com.altimetrik.cart.model.TaxDetail;
import com.altimetrik.cart.repository.entity.TaxDetails;

public interface TaxDetailService {
  TaxDetails addTaxDetails(TaxDetail detail);
  TaxDetails findByRegion(String state, String country);
}
