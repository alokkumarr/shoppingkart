package com.altimetrik.cart.repository;

import com.altimetrik.cart.repository.entity.TaxDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDetailRepository extends JpaRepository<TaxDetails, Long> {
}
