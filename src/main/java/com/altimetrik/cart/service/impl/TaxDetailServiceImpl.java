package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.States;
import com.altimetrik.cart.model.TaxDetail;
import com.altimetrik.cart.repository.TaxDetailRepository;
import com.altimetrik.cart.repository.entity.TaxDetails;
import com.altimetrik.cart.service.TaxDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxDetailServiceImpl implements TaxDetailService {

  @Autowired
  private TaxDetailRepository taxDetailRepository;

  @Override
  public TaxDetails addTaxDetails(TaxDetail detail) {
    TaxDetails taxDetails = new TaxDetails();
    taxDetails.setVat(detail.getVat());
    taxDetails.setState(States.getState(detail.getState()));
    taxDetails.setCategory(detail.getTaxCategory());
    taxDetails.setCountry(detail.getCountry());
    taxDetails.setSalesTax(detail.getSalesTax());
    taxDetails.setImportDuty(detail.getImportDuty());
    return taxDetailRepository.save(taxDetails);
  }

  @Override
  public TaxDetails findByRegion(String state, String country) {
    List<TaxDetails> taxDetails = taxDetailRepository.findAll();
    if (taxDetails != null && !taxDetails.isEmpty()) {
      return taxDetails.stream().filter(tax -> country.equalsIgnoreCase(tax.getCountry())
          && state.equalsIgnoreCase(tax.getState()))
          .findAny().get();
    }
    return taxDetailRepository.findBySate(States.getState(state));
  }

  @Override
  public List<TaxDetails> getAllTaxDetails() {
    return taxDetailRepository.findAll();
  }
}
