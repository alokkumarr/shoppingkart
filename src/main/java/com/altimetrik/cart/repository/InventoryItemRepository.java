package com.altimetrik.cart.repository;

import com.altimetrik.cart.repository.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<Item, Long> {

  @Query(value = "SELECT * FROM ITEM_DETAILS f where f.sku = :sku", nativeQuery = true)
  List<Item> findBySKU(@Param("sku") String sku);
}
