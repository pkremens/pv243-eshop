package cz.fi.muni.pv243.eshop.service;

import java.util.List;
import java.util.Map;

import cz.fi.muni.pv243.eshop.model.ProductInBasket;

/**
 * @author Matous Jobanek
 */

public interface Basket {

	void addProduct(Long productId, int quantity);

	Map<Long, Integer> getBasketContent();

	void updateProduct(long productId, int newQuantity);

	List<ProductInBasket> getAllMessages() throws Exception;

	boolean isEmpty();

	void initNewBasket();
}
