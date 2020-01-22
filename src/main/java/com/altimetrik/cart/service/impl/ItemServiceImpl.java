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
    items.getItemDetails().stream().forEach(details -> {
      Item item = new Item();
      item.setIsbn(details.getIsbn());
      item.setYear(details.getYear());
      item.setTitle(details.getTitle());
      item.setCategory(details.getCategory());
      item.setAuthor(details.getAuthor());
      item.setPublisher(details.getPublisher());
      item.setBestseller(details.getBestseller());
      item.setPrice(details.getPrice());
      item.setLanguage(details.getLanguage());
      item.setDescription(details.getDescription());
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
    it.setIsbn(details.getIsbn());
    it.setYear(details.getYear());
    it.setTitle(details.getTitle());
    it.setCategory(details.getCategory());
    it.setAuthor(details.getAuthor());
    it.setPublisher(details.getPublisher());
    it.setBestseller(details.getBestseller());
    it.setPrice(details.getPrice());
    it.setDescription(details.getDescription());
    it.setLanguage(details.getLanguage());
    return itemRepository.saveAndFlush(it);
  }


}
