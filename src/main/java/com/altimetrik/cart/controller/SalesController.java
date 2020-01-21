package com.altimetrik.cart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal server Error. Contact System administrator")
    })
public class SalesController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

  @ApiOperation(
      value = "",
      nickname = "addItem",
      notes = "This api will add the items to the cart and calculate the Tax, VAT and imported duties.",
      response = ResponseEntity.class)
  @PostMapping(value = "/addtocart")
  public Object addToCart(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestBody Object items) {
    return null;
  }

  @ApiOperation(
      value = "",
      nickname = "checkoutCart",
      notes = "This api will checkout the cart details and generate an invoice.",
      response = ResponseEntity.class)
  @GetMapping(value = "/checkout/{customerId}")
  public Object checkout(HttpServletRequest request, HttpServletResponse response,
                         @PathVariable(name = "customerId") Long customerId) {

    try {
      if (customerId <= 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "Please provide the customer Id to checkout.");
      }




    } catch (IOException ex) {
      LOGGER.error("Error occurred while checkout cart.");
    }
    return null;
  }
}
