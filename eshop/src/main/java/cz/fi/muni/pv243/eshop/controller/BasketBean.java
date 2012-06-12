package cz.fi.muni.pv243.eshop.controller;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Product;

/**
 * @author Matous Jobanek
 */
@Stateful
@SessionScoped
@Named("basket")
public class BasketBean implements Basket {

	private final static Logger LOGGER = Logger.getLogger(BasketBean.class
			.getPackage().toString());

	private final HashMap<Product, Integer> basket = new HashMap<Product, Integer>();

	@Override
	@Named
	public void addProduct(Product product, int quantity) {

		System.err.println("jboss sucks!");

		if (basket.containsKey(product)) {
			int currentQuantity = basket.get(product);
			currentQuantity += quantity;
			basket.put(product, currentQuantity);
		} else {
			basket.put(product, quantity);
		}
	}

	@Override
	public HashMap<Product, Integer> getBasketContent() {
		return basket;
	}

}
