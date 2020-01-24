package com.altimetrik.cart.service.impl;

import com.altimetrik.cart.model.AddToCartItem;
import com.altimetrik.cart.model.request.AddToCartRequest;
import com.altimetrik.cart.model.request.CustomerRef;
import com.altimetrik.cart.model.response.ATCItem;
import com.altimetrik.cart.model.response.AddToCartResponse;
import com.altimetrik.cart.repository.AddItemCartRepository;
import com.altimetrik.cart.repository.entity.AddItemCart;
import com.altimetrik.cart.repository.entity.Customer;
import com.altimetrik.cart.repository.entity.Item;
import com.altimetrik.cart.service.AddToKartService;
import com.altimetrik.cart.service.CustomerService;
import com.altimetrik.cart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AddToKartServiceImpl implements AddToKartService {

  @Autowired
  private ItemService itemService;
  @Autowired
  private CustomerService customerService;
  @Autowired
  private AddItemCartRepository cartRepository;

  @Override
  public AddToCartResponse addToKart(AddToCartRequest addToCartRequest) {
    CustomerRef custRef = addToCartRequest.getCustomerRef();
    AddToCartResponse cartResponse = new AddToCartResponse();
    if (custRef.getCustomerId() > 0) {
      Customer customer = customerService.getCustomerById(custRef.getCustomerId());
      if (customer.getId() > 0) {
        AddToCartItem cartItem = addToCartRequest.getCartItem();
        if (!StringUtils.isEmpty(cartItem.getSku())) {
          List<Item> itemList = itemService.getItemBySKU(cartItem.getSku());
          Item item = itemList != null && !itemList.isEmpty() ? itemList.get(0) : null;
          if (item != null) {
            // process add item details in table
            List<AddItemCart> list = cartRepository.findItemBySKU(item.getSku());
            if (list != null && !list.isEmpty()) {
              cartRepository.updateItemBySKU(cartItem.getQty(), list.get(0).getId());
            } else {
              ATCItem atcItem = buildItem(cartItem, item);
              AddItemCart addItemCart = getAddItemCart(customer.getId(), atcItem);
              addItemCart.setDescription(item.getBookDetails().getDescription());
              addItemCart.setImported(item.getBookDetails().getImported());
              cartRepository.save(addItemCart);
            }
          }

          // send all cart details to user
          List<AddItemCart> cartItems = cartRepository.findItemByCustomerId(customer.getId());
          cartResponse.setCartResponse(cartItems);
          cartResponse.setMessage("Item added in the cart.");
        } else {
          cartResponse.setMessage("No item available for the request.");
        }
      } else {
        cartResponse.setMessage("You are not authorized  to add kart.");
      }
    }
    return cartResponse;
  }

  @Override
  public AddToCartResponse deleteCart(AddToCartRequest addToCartRequest) {
    CustomerRef custRef = addToCartRequest.getCustomerRef();
    AddToCartResponse cartResponse = new AddToCartResponse();
    if (custRef.getCustomerId() > 0) {
      Customer customer = customerService.getCustomerById(custRef.getCustomerId());
      if (customer.getId() > 0) {
        AddToCartItem cartItem = addToCartRequest.getCartItem();
        if (!StringUtils.isEmpty(cartItem.getItemId())) {
          Long itemId = Long.valueOf(cartItem.getItemId());
          Item item = itemService.getItemById(itemId);
          List<AddItemCart> list = cartRepository.findItemBySKU(item.getSku());
          Integer deletedCart = cartRepository.deleteItemBySKU(item.getSku());
          if (deletedCart > 0) {
            cartResponse.setCartResponse(list);
            cartResponse.setMessage("Items cleared from Cart.");
          } else {
            cartResponse.setMessage("Cart Empty.");
          }
        }
      }
    }
    return cartResponse;
  }

  @Override
  public List<AddItemCart> getCartDetails(Long customerId) {
    return cartRepository.findItemByCustomerId(customerId);
  }

  @Override
  public Integer deleteCartByCustomerId(Long customerId) {
    return cartRepository.deleteItemByCategory(customerId);
  }

  /**
   * Build items details.
   *
   * @param cartItem
   * @param item
   * @return
   */
  private ATCItem buildItem(AddToCartItem cartItem, Item item) {
    ATCItem atcItem = new ATCItem();
    atcItem.setItemId(item.getId());
    atcItem.setSku(item.getSku());
    atcItem.setName(item.getName());
    atcItem.setCategory(item.getCategory());
    atcItem.setProductType(item.getBookDetails().getType());
    atcItem.setImported(item.getBookDetails().getImported());
    atcItem.setQty(cartItem.getQty());
    atcItem.setPrice(item.getPrice());
    return atcItem;
  }

  /**
   * Build the cart details by the customer id
   *
   * @param customerId
   * @param atcItem
   * @return AddItemCart
   */
  private AddItemCart getAddItemCart(Long customerId, ATCItem atcItem) {
    AddItemCart addItemCart = new AddItemCart();
    addItemCart.setItemId(atcItem.getItemId());
    addItemCart.setCustomerId(customerId);
    addItemCart.setQuantity(atcItem.getQty());
    addItemCart.setName(atcItem.getName());
    addItemCart.setProductType(atcItem.getProductType());
    addItemCart.setPrice(atcItem.getPrice());
    addItemCart.setSku(atcItem.getSku());
    addItemCart.setCategory(atcItem.getCategory());
    return addItemCart;
  }
}
