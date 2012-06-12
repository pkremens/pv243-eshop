
package cz.fi.muni.pv243.eshop.controller;

import java.util.HashMap;

import javax.ejb.Remote;

import cz.fi.muni.pv243.eshop.model.Product;

/**
 * @author Matous Jobanek
 */
@Remote
public interface Basket {
	
   public void addProduct(Product product, int quantity);

   public HashMap<Product, Integer> getBasketContent();
}
