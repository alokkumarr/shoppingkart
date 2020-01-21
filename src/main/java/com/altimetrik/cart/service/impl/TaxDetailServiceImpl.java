package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.TaxDetail;
import com.altimetrik.cart.repository.TaxDetailRepository;
import com.altimetrik.cart.repository.entity.TaxDetails;
import com.altimetrik.cart.service.TaxDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxDetailServiceImpl implements TaxDetailService {

  @Autowired
  private TaxDetailRepository taxDetailRepository;

  @Override
  public TaxDetails addTaxDetails(TaxDetail detail) {
    TaxDetails taxDetails = new TaxDetails();
    taxDetails.setVat(detail.getVat());
    taxDetails.setCategory(detail.getTaxCategory());
    taxDetails.setCountry(detail.getCountry());
    taxDetails.setSalesTax(detail.getSalesTax());
    taxDetails.setImportDuty(detail.getImportDuty());
    return taxDetailRepository.save(taxDetails);
  }

}
