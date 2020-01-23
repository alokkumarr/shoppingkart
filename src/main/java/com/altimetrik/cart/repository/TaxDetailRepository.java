package com.altimetrik.cart.repository;

import com.altimetrik.cart.repository.entity.TaxDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDetailRepository extends JpaRepository<TaxDetails, Long> {

  @Query(value = "SELECT * FROM TAX_DETAILS TD WHERE TD.STATE = (:state)", nativeQuery = true)
  TaxDetails findBySate(@Param("state") String state);
}
