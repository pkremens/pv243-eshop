package cz.fi.muni.pv243.eshop.controller;

import java.util.HashMap;

/**
 * @author Matous Jobanek
 */
public interface Basket {

	public void addProduct(Long productId, int quantity);

	public HashMap<Long, Integer> getBasketContent();

	// public ProductInBasket getProductInBasket();
}
