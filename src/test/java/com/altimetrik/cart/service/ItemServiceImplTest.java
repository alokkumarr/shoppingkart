package com.altimetrik.cart.service;

import com.altimetrik.cart.model.Items;
import com.altimetrik.cart.repository.entity.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest extends BaseTest {

  @Mock
  private ItemService itemService;
  private Items items = new Items();

  @Before
  public void before() throws Exception {
    File node = getJsonFile("samplejson/additem.json");
    items = mapper.readValue(node, Items.class);
  }

  @Test
  public void testMockCreation() {
    Assert.assertNotNull(itemService);
  }

  @Test
  public void testAddItems() {
    List<Item> itemList = getItems();
    Mockito.lenient().when(itemService.addItemDetails(items)).thenReturn(itemList);

    Assert.assertEquals(1, itemService.addItemDetails(items).size());
    Assert.assertEquals("IB123", itemService.addItemDetails(items).get(0).getSku());
  }

  @Test
  public void testGetItem() {
    List<Item> itemList = getItems();
    Mockito.lenient().when(itemService.getItems()).thenReturn(itemList);
    Assert.assertEquals(1, itemService.getItems().size());
  }

  @Test
  public void testGetItemByCustomerCode() {
    List<Item> itemList = getItems();
    Mockito.lenient().when(itemService.getItemBySKU("IB123")).thenReturn(itemList);
    Assert.assertEquals(1, itemService.getItemBySKU("IB123").size());
    Assert.assertEquals("IB123", itemService.getItemBySKU("IB123").get(0).getSku());
  }
}
