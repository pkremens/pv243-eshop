package cz.fi.muni.pv243.eshop.service;

import java.util.HashMap;
import java.util.List;

import cz.fi.muni.pv243.eshop.model.ProductInBasket;

/**
 * @author Matous Jobanek
 */
public interface Basket {

	public void addProduct(Long productId, int quantity);

	public HashMap<Long, Integer> getBasketContent();

	public void updateProduct(long productId, int newQuantity);

	public List<ProductInBasket> getAllMessages() throws Exception;
}
