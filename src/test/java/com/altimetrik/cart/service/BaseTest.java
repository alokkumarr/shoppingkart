package com.altimetrik.cart.service;

import com.altimetrik.cart.repository.entity.AddItemCart;
import com.altimetrik.cart.repository.entity.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

  protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
  protected ObjectMapper mapper = new ObjectMapper();

  /**
   * Read Json data from classpath.
   *
   * @param classpath Classpath file.
   * @return String
   * @throws IOException when classpath file does not exists
   */
  public File getJsonFile(String classpath) {
    try {
      return new ClassPathResource(classpath).getFile();
    } catch (IOException e) {
      LOGGER.error("Exception occurred while reading the file from class path {}", e);
    }
    return null;
  }

  protected List<Item> getItems() {
    List<Item> itemList = new ArrayList<>();
    Item item = new Item();
    item.setId(1L);
    item.setSku("IB123");
    item.setPrice(123.98);
    item.setCategory("Novel");
    item.setName("One India Girl");
    itemList.add(item);
    return itemList;
  }

  protected AddItemCart getAddItemCart() {
    AddItemCart cartItem = new AddItemCart();
    cartItem.setItemId(123L);
    cartItem.setSku("IB125");
    cartItem.setPrice(123.65);
    cartItem.setName("TestName");
    cartItem.setCustomerId(1l);
    cartItem.setQuantity(2);
    cartItem.setCategory("Book");
    cartItem.setProductType("Management");
    cartItem.setDescription("Finalcial Design");
    return cartItem;
  }

}
