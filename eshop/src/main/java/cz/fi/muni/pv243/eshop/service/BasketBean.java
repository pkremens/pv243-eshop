package cz.fi.muni.pv243.eshop.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.ejb3.annotation.Clustered;

import cz.fi.muni.pv243.eshop.model.ProductInBasket;

/**
 * @author Matous Jobanek
 */
@Clustered
@Stateful
@SessionScoped
@Named("basket")
public class BasketBean implements Basket {

	@Inject
	private ProductManager productManager;

	private final Map<Long, Integer> basket = new ConcurrentHashMap<Long, Integer>();

	@Inject
	private Logger log;

	@Override
	@Named
	public void addProduct(Long productId, int quantity) {

		System.err.println(productId + " " + quantity);
		if (quantity > 0) {
			if (basket.containsKey(productId)) {
				int currentQuantity = basket.get(productId);
				currentQuantity += quantity;
				basket.put(productId, currentQuantity);
			} else {
				basket.put(productId, quantity);
			}

			for (Long key : basket.keySet()) {
				System.err.println("key: " + key);
			}
		} else {
			log.info("adding 0 products");
		}
	}

	@Override
	public Map<Long, Integer> getBasketContent() {
		return basket;
	}

	@Override
	public void updateProduct(long productId, int newQuantity) {

		if (basket.containsKey(productId)) {
			basket.remove(productId);
			if (newQuantity > 0) {
				basket.put(productId, newQuantity);
			}
		}
	}

	@Override
	public List<ProductInBasket> getAllMessages() throws Exception {
		List<ProductInBasket> products = new ArrayList<ProductInBasket>();

		for (long productId : basket.keySet()) {
			// HtmlOutputText htmlOutputText = new HtmlOutputText();
			// htmlOutputText.setValue(productId);
			ProductInBasket productInBasket = new ProductInBasket(
					productManager.findProduct(productId),
					basket.get(productId));
			// productInBasket.setProduct();
			products.add(productInBasket);

		}

		return products;
	}

	@Override
	public boolean isEmpty() {
		return basket.isEmpty();
	}

}
