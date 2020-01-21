package com.altimetrik.kart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Items {

  @JsonProperty("items")
  List<ItemDetails> itemDetails;

  public List<ItemDetails> getItemDetails() {
    return itemDetails;
  }

  public void setItemDetails(List<ItemDetails> itemDetails) {
    this.itemDetails = itemDetails;
  }
}
