package com.altimetrik.cart.service;

import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.response.AddToCartResponse;
import com.altimetrik.cart.repository.entity.AddItemCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class AddToKartServiceTest extends BaseTest {

  AddToCartResponse cartResponse = null;
  @Mock
  private AddToKartService kartService;
  private AddToCartRequest cartRequest = null;

  @Before
  public void init() throws Exception {
    File node = getJsonFile("samplejson/addcart.json");
    cartRequest = mapper.readValue(node, AddToCartRequest.class);

    cartResponse = new AddToCartResponse();
    AddItemCart cartItem = getAddItemCart();
    cartResponse.setCartResponse(Arrays.asList(cartItem));
  }

  @Test
  public void testConfiguration() {
    Assert.assertNotNull(cartRequest);
  }

  @Test
  public void testAddToCart() {
    Mockito.lenient().when(kartService.addToKart(cartRequest)).thenReturn(cartResponse);

    Assert.assertNotNull(kartService.addToKart(cartRequest).getCartResponse());
  }
}
