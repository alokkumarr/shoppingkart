package com.altimetrik.cart.service.utils;

import com.altimetrik.cart.model.CustomerDetails;
import com.altimetrik.cart.model.DiscountDetails;
import com.altimetrik.cart.model.Items;
import com.altimetrik.cart.model.TaxDetail;
import com.altimetrik.cart.service.CustomerService;
import com.altimetrik.cart.service.DiscountService;
import com.altimetrik.cart.service.ItemService;
import com.altimetrik.cart.service.TaxDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

@Service
public class LoadOnStartUpService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoadOnStartUpService.class);
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private ItemService itemService;
  @Autowired
  private CustomerService customerService;
  @Autowired
  private DiscountService discountService;
  @Autowired
  private TaxDetailService taxDetailService;


  public void catalogLoadOnStartUp() {
    try {
      // load customer data
      ObjectNode customerData = getJsonObject("/sample-json/customer.json");
      CustomerDetails customerDetails = mapper.readValue(customerData.toString(), CustomerDetails.class);
      customerService.addCustomer(customerDetails);

      // load discount data
      ObjectNode itemsData = getJsonObject("/sample-json/items.json");
      Items items = mapper.readValue(itemsData.toString(), Items.class);
      itemService.addItemDetails(items);

      // load discount data
      ObjectNode discountData = getJsonObject("/sample-json/discounts.json");
      DiscountDetails discountDetails = mapper.readValue(discountData.toString(), DiscountDetails.class);
      discountService.addDiscountDetails(discountDetails);

      // load discount data
      ObjectNode taxData = getJsonObject("/sample-json/taxdetails.json");
      TaxDetail taxDetail = mapper.readValue(taxData.toString(), TaxDetail.class);
      taxDetailService.addTaxDetails(taxDetail);
    } catch (Exception ex) {
      LOGGER.error("Error occurred during Catalog load on start up.");
    }
  }

  /**
   * Read Json data from classpath.
   *
   * @param classpath Classpath file.
   * @return String
   * @throws IOException when classpath file does not exists
   */
  public ObjectNode getJsonObject(String classpath) {
    try {
      Resource resource = new ClassPathResource(classpath);
      InputStream inputStream = resource.getInputStream();
      byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
      return (ObjectNode) mapper.readTree(bdata);
    } catch (IOException e) {
      LOGGER.error("Exception occurred while reading the file from class path {}", e);
    }
    return null;
  }
}
