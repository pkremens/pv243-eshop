
package cz.fi.muni.pv243.eshop.controller;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import cz.fi.muni.pv243.eshop.model.Product;

/**
 * @author Matous Jobanek
 */
@Stateful
public class BasketBean implements Basket {

   private final static Logger LOGGER = Logger.getLogger(BasketBean.class.getPackage().toString());

   private HashMap<Product, Integer> basket = new HashMap<Product, Integer>();

   public void addProduct(Product product, int quantity) {
      if (basket.containsKey(product)) {
         int currentQuantity = basket.get(product);
         currentQuantity += quantity;
         basket.put(product, currentQuantity);
      } else {
         basket.put(product, quantity);
      }
   }

   public HashMap<Product, Integer> getBasketContent() {
      return basket;
   }

}
