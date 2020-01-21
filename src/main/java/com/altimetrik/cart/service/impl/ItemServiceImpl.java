package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.ItemDetails;
import com.altimetrik.cart.model.Items;
import com.altimetrik.cart.repository.ItemRepository;
import com.altimetrik.cart.repository.entity.Item;
import com.altimetrik.cart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  private ItemRepository itemRepository;

  @Override
  public List<Item> addItemDetails(Items items) {
    List<Item> itemList = new ArrayList<>();
    items.getItemDetails().stream().forEach(itemDetails -> {
      Item item = new Item();
      item.setName(itemDetails.getName());
      item.setType(itemDetails.getType());
      item.setPrice(itemDetails.getPrice());
      item.setDescription(itemDetails.getDescription());
      itemList.add(item);
    });
    return itemRepository.saveAll(itemList);
  }

  @Override
  public Item getItemById(Long id) {
    return itemRepository.findById(id).get();
  }

  @Override
  public List<Item> getItems() {
    return itemRepository.findAll();
  }

  @Override
  public void deleteItems() {
    itemRepository.deleteAll();
  }

  @Override
  public void deleteItemById(Long id) {
    itemRepository.deleteById(id);
  }

  @Override
  public Item updateItem(Items items) {
    Item it = new Item();
    ItemDetails details = items.getItemDetails().get(0);
    it.setId(details.getId());
    it.setType(details.getType());
    it.setName(details.getName());
    it.setDescription(details.getDescription());
    it.setPrice(details.getPrice());
    return itemRepository.saveAndFlush(it);
  }


}
