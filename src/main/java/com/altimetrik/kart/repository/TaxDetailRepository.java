package com.altimetrik.kart.repository;

import com.altimetrik.kart.repository.entity.TaxDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDetailRepository extends JpaRepository<TaxDetails, Long> {
}
