package com.altimetrik.cart.repository;

import com.altimetrik.cart.repository.entity.AddItemCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AddItemCartRepository extends CrudRepository<AddItemCart, Long> {

  @Query(value = "SELECT * FROM ADD_TO_CART f where f.sku = :sku", nativeQuery = true)
  List<AddItemCart> findItemBySKU(@Param("sku") String sku);

  @Modifying
  @Transactional
  @Query(value = "UPDATE ADD_TO_CART f SET f.quantity = :qty where f.cart_id = :id", nativeQuery = true)
  void updateItemBySKU(@Param("qty") Integer qty, @Param("id") Long id);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM ADD_TO_CART f where f.sku = :sku", nativeQuery = true)
  Integer deleteItemBySKU(@Param("sku") String sku);

}
