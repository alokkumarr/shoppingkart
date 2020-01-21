package com.altimetrik.cart.repository;

import com.altimetrik.cart.repository.entity.SalesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDetailRepository extends JpaRepository<SalesDetail, Long> {
}
