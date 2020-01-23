package com.altimetrik.cart.service;


import com.altimetrik.cart.model.Items;
import com.altimetrik.cart.repository.entity.Item;

import java.util.List;

public interface ItemService {

  List<Item> addItemDetails(Items itemDetails);
  Item getItemById(Long id);
  List<Item> getItemBySKU(String sku);
  List<Item> getItems();
  void deleteItems();
  void deleteItemById(Long id);
  Item updateItem(Items item);
}
