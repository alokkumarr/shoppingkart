package com.altimetrik.cart.model;

import java.util.HashMap;
import java.util.Map;

public class States {

  private static Map<String, String> map = new HashMap<>();

  static {
    map.put("Andhra Pradesh", "AP");
    map.put("Arunachal Pradesh", "AR");
    map.put("Assam", "AS");
    map.put("Bihar", "BR");
    map.put("Chhattisgarh", "CG");
    map.put("Goa", "GA");
    map.put("Gujarat", "GJ");
    map.put("Haryana", "HR");
    map.put("Himachal Pradesh", "HP");
    map.put("Jammu and Kashmir", "JK");
    map.put("Jharkhand", "JH");
    map.put("Karnataka", "KA");
    map.put("Kerala", "KL");
  }

  public static String getState(String name) {
    return map.get(name);
  }

}
