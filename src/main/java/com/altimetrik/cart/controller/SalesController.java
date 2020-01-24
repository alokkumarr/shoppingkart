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

  private static final Logger LOGGER = LoggerFactory.getLogger(SalesController.class);

  private static final String CUSTOMER_ERROR = "Please provide the correct customer Id.";
  private static final String CART_MESSAGE = "Please register to add item in the cart.";
  private static String Error = "Error during the item to the cart {}.";
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
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, CART_MESSAGE);
        cartResponse.setMessage(CART_MESSAGE);
        return cartResponse;
      }
      Long customerId = Long.valueOf(custRef.getCustomerId());
      if (customerId <= 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, CUSTOMER_ERROR);
      }

      cartResponse = kartService.addToKart(cartRequest);
    } catch (NumberFormatException ex) {
      LOGGER.error(Error, ex);
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      cartResponse.setMessage(CUSTOMER_ERROR);
    } catch (IOException ex) {
      LOGGER.error(Error, ex);
    }
    return cartResponse;
  }

  @ApiOperation(
      value = "",
      nickname = "deleteCart",
      notes = "This api will add the items to the cart and calculate the Tax, VAT and imported duties.",
      response = ResponseEntity.class)
  @DeleteMapping(value = "/cart")
  public Object deleteCart(HttpServletRequest request,
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
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, CART_MESSAGE);
        cartResponse.setMessage(CART_MESSAGE);
        return cartResponse;
      }

      Long customerId = Long.valueOf(custRef.getCustomerId());
      if (customerId <= 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "Please provide the customer Id to checkout.");
      }


      cartResponse = kartService.deleteCart(cartRequest);
    } catch (NumberFormatException ex) {
      LOGGER.error(Error, ex);
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      cartResponse.setMessage(CUSTOMER_ERROR);
    } catch (IOException ex) {
      LOGGER.error(Error, ex);
    }
    return cartResponse;
  }

  @ApiOperation(
      value = "",
      nickname = "getCartDetails",
      notes = "This api will fetch the items from the cart.",
      response = ResponseEntity.class)
  @GetMapping(value = "/cart/{customerId}")
  public Object fetchCartDetails(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @PathVariable(name = "customerId") Long customerId) {

    AddToCartResponse cartResponse = new AddToCartResponse();
    try {
      if (customerId == null || customerId <= 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, MISSING_REQUEST);
        cartResponse.setMessage(CUSTOMER_ERROR);
        return cartResponse;
      }
      return kartService.getCartDetails(customerId);
    } catch (IOException ex) {
      LOGGER.error("Error during the item to the cart {}", ex);
    }
    cartResponse.setMessage("No details available for cart.");
    return cartResponse;
  }


  @ApiOperation(
      value = "",
      nickname = "checkoutCart",
      notes = "This api will checkout the cart details and generate an invoice.",
      response = ResponseEntity.class)
  @GetMapping(value = "/checkout/{customerId}")
  public CheckOutResponse checkout(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable(name = "customerId") String custId) {

    CheckOutResponse outResponse = new CheckOutResponse();

    try {
      Long customerId = Long.valueOf(custId);
      if (customerId <= 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "Please provide the customer Id to checkout.");
      }
      Receipt receipt = salesService.checkOut(customerId);
      if (receipt != null && receipt.getProductItems() != null) {
        outResponse.setReceiptDetails(receipt);
        outResponse.setMessage("Receipt created.");

        // clear the cart details
        kartService.deleteCartByCustomerId(customerId);
      } else {
        outResponse.setMessage("Your cart is empty. Please add item to the cart.");
      }
    } catch (NumberFormatException ex) {
      LOGGER.error(Error, ex);
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      outResponse.setMessage(CUSTOMER_ERROR);
    } catch (IOException ex) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      LOGGER.error("Error occurred while checkout cart.");
    }
    return outResponse;
  }
}
