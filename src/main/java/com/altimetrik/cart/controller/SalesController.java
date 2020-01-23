package com.altimetrik.cart.controller;

import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.request.CustomerRef;
import com.altimetrik.cart.model.response.AddToCartResponse;
import com.altimetrik.cart.model.response.CheckOutResponse;
import com.altimetrik.cart.model.response.Receipt;
import com.altimetrik.cart.service.AddToKartService;
import com.altimetrik.cart.service.SalesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/user/sales")
@Api(value = "The controller provides to perform all the admin related operations.")
@ApiResponses(
    value = {
        @ApiResponse(code = 202, message = "Request has been accepted without any error"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal server Error. Contact System administrator")
    })
public class SalesController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

  private static final String MISSING_REQUEST = "Missing request body to add item in the cart.";

  @Autowired
  private SalesService salesService;
  @Autowired
  private AddToKartService kartService;

  @ApiOperation(
      value = "",
      nickname = "addItem",
      notes = "This api will add the items to the cart and calculate the Tax, VAT and imported duties.",
      response = ResponseEntity.class)
  @PostMapping(value = "/cart")
  public Object addCart(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestBody(required = false) AddToCartRequest cartRequest) {

    AddToCartResponse cartResponse = new AddToCartResponse();
    try {
      if (cartRequest == null) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, MISSING_REQUEST);
        cartResponse.setMessage(MISSING_REQUEST);
        return cartResponse;
      }

      CustomerRef custRef = cartRequest.getCustomerRef();
      if (custRef == null) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "Please register to add item in the cart.");
        cartResponse.setMessage("Please register to add item in the cart.");
        return cartResponse;
      }
      cartResponse = kartService.addToKart(cartRequest);
    } catch (IOException ex) {
      LOGGER.error("Error during the item to the cart {}", ex);
    }
    return cartResponse;
  }

  @ApiOperation(
      value = "",
      nickname = "addItem",
      notes = "This api will add the items to the cart and calculate the Tax, VAT and imported duties.",
      response = ResponseEntity.class)
  @DeleteMapping(value = "/cart")
  public Object updateCart(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestBody(required = false) AddToCartRequest cartRequest) {

    AddToCartResponse cartResponse = new AddToCartResponse();
    try {
      if (cartRequest == null) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, MISSING_REQUEST);
        cartResponse.setMessage(MISSING_REQUEST);
        return cartResponse;
      }

      CustomerRef custRef = cartRequest.getCustomerRef();
      if (custRef == null) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "Please register to add item in the cart.");
        cartResponse.setMessage("Please register to add item in the cart.");
        return cartResponse;
      }
      cartResponse = kartService.deleteCart(cartRequest);
    } catch (IOException ex) {
      LOGGER.error("Error during the item to the cart {}", ex);
    }
    return cartResponse;
  }


  @ApiOperation(
      value = "",
      nickname = "checkoutCart",
      notes = "This api will checkout the cart details and generate an invoice.",
      response = ResponseEntity.class)
  @GetMapping(value = "/checkout/{customerId}")
  public CheckOutResponse checkout(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable(name = "customerId") Long customerId) {

    CheckOutResponse outResponse = new CheckOutResponse();
    try {
      if (customerId <= 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "Please provide the customer Id to checkout.");
      }
      Receipt receipt = salesService.checkOut(customerId);
      outResponse.setReceiptDetails(receipt);
      outResponse.setMessage("Receipt created.");
    } catch (IOException ex) {
      LOGGER.error("Error occurred while checkout cart.");
    }
    return outResponse;
  }
}
