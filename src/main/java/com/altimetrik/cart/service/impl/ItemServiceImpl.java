package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.ItemDetails;
import com.altimetrik.cart.model.Items;
import com.altimetrik.cart.repository.InventoryItemRepository;
import com.altimetrik.cart.repository.entity.BookDetails;
import com.altimetrik.cart.repository.entity.Item;
import com.altimetrik.cart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  private InventoryItemRepository inventoryItemRepository;

  @Override
  public List<Item> addItemDetails(Items items) {
    List<Item> itemList = new ArrayList<>();
    items.getItemDetails().stream().forEach(details -> {
      Item item = new Item();
      item.setName(details.getName());
      item.setSku(details.getSku());
      item.setCategory(details.getCategory());
      item.setPrice(details.getPrice());
      if ("Book".equalsIgnoreCase(details.getCategory())) {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setYear(details.getYear());
        bookDetails.setType(details.getType());
        bookDetails.setAuthor(details.getAuthor());
        bookDetails.setPublisher(details.getPublisher());
        bookDetails.setBestseller(details.getBestseller());
        bookDetails.setLanguage(details.getLanguage());
        bookDetails.setDescription(details.getDescription());
        item.setBookDetails(bookDetails);
      }

      itemList.add(item);
    });
    return inventoryItemRepository.saveAll(itemList);
  }

  @Override
  public Item getItemById(Long id) {
    return inventoryItemRepository.findById(id).get();
  }

  @Override
  public List<Item> getItemBySKU(String sku) {
    return inventoryItemRepository.findBySKU(sku);
  }

  @Override
  public List<Item> getItems() {
    return inventoryItemRepository.findAll();
  }

  @Override
  public void deleteItems() {
    inventoryItemRepository.deleteAll();
  }

  @Override
  public void deleteItemById(Long id) {
    inventoryItemRepository.deleteById(id);
  }

  @Override
  public Item updateItem(Items items) {
    Item it = new Item();
    ItemDetails details = items.getItemDetails().get(0);
    it.setId(details.getId());
    it.setSku(details.getSku());
    it.setName(details.getName());
    it.setCategory(details.getCategory());
    it.setPrice(details.getPrice());
    if ("Book".equalsIgnoreCase(details.getCategory())) {
      BookDetails bookDetails = new BookDetails();
      bookDetails.setYear(details.getYear());
      bookDetails.setType(details.getType());
      bookDetails.setAuthor(details.getAuthor());
      bookDetails.setPublisher(details.getPublisher());
      bookDetails.setBestseller(details.getBestseller());
      bookDetails.setLanguage(details.getLanguage());
      bookDetails.setDescription(details.getDescription());
      it.setBookDetails(bookDetails);
    }
    return inventoryItemRepository.saveAndFlush(it);
  }


}
