package cz.fi.muni.pv243.eshop.service;

import java.util.HashMap;
import java.util.List;

import cz.fi.muni.pv243.eshop.model.ProductInBasket;

/**
 * @author Matous Jobanek
 */

public interface Basket {

	void addProduct(Long productId, int quantity);

	HashMap<Long, Integer> getBasketContent();

	void updateProduct(long productId, int newQuantity);

	List<ProductInBasket> getAllMessages() throws Exception;

	boolean isEmpty();
}
