package com.altimetrik.cart.model.response;

import java.io.Serializable;

public class CheckOutResponse implements Serializable {

  private Receipt receiptDetails;
  private String message;

  public Receipt getReceiptDetails() {
    return receiptDetails;
  }

  public void setReceiptDetails(Receipt receiptDetails) {
    this.receiptDetails = receiptDetails;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
