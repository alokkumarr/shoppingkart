package com.altimetrik.kart.service;


import com.altimetrik.kart.model.Items;
import com.altimetrik.kart.repository.entity.Item;

import java.util.List;

public interface ItemService {

  List<Item> addItemDetails(Items itemDetails);
  Item getItemById(Long id);
  List<Item> getItems();
  void deleteItems();
  void deleteItemById(Long id);
  Item updateItem(Items item);
}
