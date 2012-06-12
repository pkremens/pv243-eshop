package cz.fi.muni.pv243.eshop.controller;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author Matous Jobanek
 */
@Stateful
@SessionScoped
@Named("basket")
public class BasketBean implements Basket {

	private final static Logger LOGGER = Logger.getLogger(BasketBean.class
			.getPackage().toString());

	private final HashMap<Long, Integer> basket = new HashMap<Long, Integer>();

	@Override
	@Named
	public void addProduct(Long product, int quantity) {

		if (basket.containsKey(product)) {
			int currentQuantity = basket.get(product);
			currentQuantity += quantity;
			basket.put(product, currentQuantity);
		} else {
			basket.put(product, quantity);
		}

		// for (Long key : basket.keySet()) {
		// System.err.println(key);
		// }
	}

	@Override
	public HashMap<Long, Integer> getBasketContent() {
		return basket;
	}

}
