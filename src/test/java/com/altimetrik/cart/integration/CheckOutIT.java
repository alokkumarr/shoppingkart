package com.altimetrik.cart.integration;

import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.response.AddToCartResponse;
import com.altimetrik.cart.model.response.CheckOutResponse;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckOutIT extends BaseIT {

  HttpHeaders headers = new HttpHeaders();
  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpEntity<AddToCartRequest> entity;
  ResponseEntity<CheckOutResponse> response;
  private String uri;

  @Before
  public void init() {
    uri = uri("user/sales/checkout");
    entity = new HttpEntity<>(null, headers);
  }

  @Test
  public void testReceiptWithWrongUser() {
    // test with string user
    response = restTemplate.exchange(uri + "/test", HttpMethod.GET, entity, CheckOutResponse.class);
    Assert.assertEquals(400, response.getStatusCode().value());

    // test with -1 value for user id
    response = restTemplate.exchange(uri + "/-1", HttpMethod.GET, entity, CheckOutResponse.class);
    Assert.assertEquals(400, response.getStatusCode().value());
  }

  @Test
  public void testReceiptWithEmptyCart() {
    // test with empty cart user
    response = restTemplate.exchange(uri + "/1", HttpMethod.GET, entity, CheckOutResponse.class);
    Assert.assertEquals(200, response.getStatusCode().value());
    Assert.assertEquals("Your cart is empty. Please add item to the cart.", response.getBody().getMessage());
  }

  @Test
  public void testReceiptWithItem() {
    HttpEntity<AddToCartRequest> entity = new HttpEntity<>(getAddItemRequest(), headers);
    restTemplate.exchange(uri("/user/sales/cart"), HttpMethod.POST, entity, AddToCartResponse.class);

    // test with empty cart user
    response = restTemplate.exchange(uri + "/1", HttpMethod.GET, entity, CheckOutResponse.class);
    Assert.assertEquals(200, response.getStatusCode().value());
    Assert.assertEquals("Receipt created.", response.getBody().getMessage());
  }
}
