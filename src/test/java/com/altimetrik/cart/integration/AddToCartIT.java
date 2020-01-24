package com.altimetrik.cart.integration;

import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.response.AddToCartResponse;
import com.altimetrik.cart.repository.entity.AddItemCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddToCartIT extends BaseIT {


  ResponseEntity<AddToCartResponse> response;
  HttpHeaders headers = new HttpHeaders();
  TestRestTemplate restTemplate = new TestRestTemplate();
  private AddToCartRequest cartRequest;
  private String uri;

  @Before
  public void init() throws Exception {
    cartRequest = getAddItemRequest();
    uri = uri("/user/sales/cart");
  }

  @Test
  public void testAddItemToCart() {
    HttpEntity<AddToCartRequest> entity = new HttpEntity<>(cartRequest, headers);
    response = restTemplate.exchange(uri, HttpMethod.POST, entity, AddToCartResponse.class);

    Assert.assertNotNull(response.getBody());
    Assert.assertEquals("Item added in the cart.", response.getBody().getMessage());

    List<AddItemCart> cartResponse = response.getBody().getCartResponse();
    Assert.assertEquals("My Gita", cartResponse.get(0).getName());
  }

  @Test
  public void testInvalidCustomerId() {
    cartRequest.getCustomerRef().setCustomerId("abc");
    HttpEntity<AddToCartRequest> entity = new HttpEntity<>(cartRequest, headers);
    response = restTemplate.exchange(uri, HttpMethod.POST, entity, AddToCartResponse.class);

    Assert.assertNotNull(response.getBody());
    Assert.assertEquals("Please provide the correct customer Id.", response.getBody().getMessage());
  }

  @Test
  public void testWrongTypeQuantity() {
    cartRequest.getCartItem().setQty("1.2");
    HttpEntity<AddToCartRequest> entity = new HttpEntity<>(cartRequest, headers);
    response = restTemplate.exchange(uri, HttpMethod.POST, entity, AddToCartResponse.class);

    Assert.assertNotNull(response.getBody());
    Assert.assertEquals("Please provide the correct format to request.", response.getBody().getMessage());
  }

  @Test
  public void testWrongItemId() {
    cartRequest.getCartItem().setItemId("ITEM_ID");
    HttpEntity<AddToCartRequest> entity = new HttpEntity<>(cartRequest, headers);
    response = restTemplate.exchange(uri, HttpMethod.POST, entity, AddToCartResponse.class);

    Assert.assertNotNull(response.getBody());
    Assert.assertEquals("Please provide the correct format to request.", response.getBody().getMessage());
  }
}
