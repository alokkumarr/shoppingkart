package com.altimetrik.cart.controller;

import com.altimetrik.cart.model.DiscountDetails;
import com.altimetrik.cart.model.Items;
import com.altimetrik.cart.model.TaxDetail;
import com.altimetrik.cart.repository.entity.Discount;
import com.altimetrik.cart.repository.entity.Item;
import com.altimetrik.cart.repository.entity.TaxDetails;
import com.altimetrik.cart.service.DiscountService;
import com.altimetrik.cart.service.ItemService;
import com.altimetrik.cart.service.TaxDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/catalog")
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
public class AdminCatalogController {

  @Autowired
  private ItemService itemService;
  @Autowired
  private DiscountService discountService;
  @Autowired
  private TaxDetailService taxDetailService;


  @ApiOperation(
      value = "",
      nickname = "addItem",
      notes = "This api will add the items details to item catalog.",
      response = ResponseEntity.class)
  @PostMapping(value = "/item")
  public ResponseEntity<List<Item>> addItemDetails(HttpServletResponse response,
                                                   @RequestBody Items items) {
    if (items == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    List<Item> list = itemService.addItemDetails(items);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }


  @ApiOperation(
      value = "",
      nickname = "updateItem",
      notes = "",
      response = ResponseEntity.class)
  @PutMapping(value = "/item")
  public ResponseEntity<Item> updateItemDetails(HttpServletResponse response,
                                                @RequestBody Items items) {
    if (items == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
    Item list = itemService.updateItem(items);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @ApiOperation(
      value = "",
      nickname = "fetchItemDetailsById",
      notes = "",
      response = ResponseEntity.class)
  @GetMapping(value = "/item/{id}")
  public Item fetchItemById(@PathVariable(name = "id") Long id) {
    return itemService.getItemById(id);
  }

  @ApiOperation(
      value = "",
      nickname = "deleteItemDetailsById",
      notes = "",
      response = ResponseEntity.class)
  @DeleteMapping(value = "/item/{id}")
  public String deleteItemById(@PathVariable(name = "id") Long id) {
    itemService.deleteItemById(id);
    return "Item deleted successfully.";
  }

  @ApiOperation(
      value = "",
      nickname = "deleteItemDetailsById",
      notes = "",
      response = ResponseEntity.class)
  @DeleteMapping(value = "/item")
  public String deleteItems() {
    itemService.deleteItems();
    return "All Item deleted successfully.";
  }


  @ApiOperation(
      value = "",
      nickname = "fetchAllItemDetails",
      notes = "",
      response = ResponseEntity.class)
  @GetMapping(value = "/item")
  public ResponseEntity<List<Item>> fetchAllItems() {
    List<Item> list = itemService.getItems();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @ApiOperation(
      value = "",
      nickname = "addDiscountDetails",
      notes = "This api will add the discount details to item catalog.",
      response = ResponseEntity.class)
  @PostMapping(value = "/discount")
  public ResponseEntity<List<Discount>> addDiscountDetails(HttpServletResponse response,
                                                   @RequestBody DiscountDetails details) {
    if (details == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
    List<Discount> list = discountService.addDiscountDetails(details);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @ApiOperation(
      value = "",
      nickname = "addTaxDetails",
      notes = "This api will add the tax details in to catalog.",
      response = ResponseEntity.class)
  @PostMapping(value = "/tax")
  public ResponseEntity<TaxDetails> addTaxDetails(HttpServletResponse response,
                                                           @RequestBody TaxDetail details) {
    if (details == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
    TaxDetails list = taxDetailService.addTaxDetails(details);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }
}
