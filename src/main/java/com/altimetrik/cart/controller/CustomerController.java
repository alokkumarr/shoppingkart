package com.altimetrik.cart.controller;

import com.altimetrik.cart.model.CustomerDetails;
import com.altimetrik.cart.repository.entity.Customer;
import com.altimetrik.cart.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@ApiResponses(
    value = {
        @ApiResponse(code = 202, message = "Request has been accepted without any error"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(
            code = 403,
            message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal server Error. Contact System administrator")
    })
@Api(value = "The controller provides to perform all the customer related operations.")
public class CustomerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  @ApiOperation(
      value = "",
      nickname = "addCustomerDetails",
      notes = "",
      response = ResponseEntity.class)
  @PostMapping(value = "")
  public ResponseEntity<Customer> addCustomer(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody CustomerDetails details) {
    Customer customer = new Customer();
    try {
      if (details == null) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
            "Customer details are missing.");
      }
      customer = customerService.addCustomer(details);
    } catch (IOException e) {
      LOGGER.error("Error occurred while adding customer.");
    }
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }


  @ApiOperation(
      value = "",
      nickname = "fetchCustomerDetailsById",
      notes = "",
      response = ResponseEntity.class)
  @GetMapping(value = "/{id}")
  public Object getCustomerById(@PathVariable(name = "id") Long id) {
    return customerService.getCustomerById(id);
  }


  @ApiOperation(
      value = "",
      nickname = "fetchAllCustomerDetails",
      notes = "",
      response = ResponseEntity.class)
  @GetMapping(value = "")
  public ResponseEntity<List<Customer>> getCustomers() {
    List<Customer> list = customerService.getCustomers();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }
}
