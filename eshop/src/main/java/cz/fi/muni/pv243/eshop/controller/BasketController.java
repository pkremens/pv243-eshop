package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.OrderLine;
import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.model.ProductInBasket;
import cz.fi.muni.pv243.eshop.model.ProductToBasket;
import cz.fi.muni.pv243.eshop.model.ProductUpdateBasket;
import cz.fi.muni.pv243.eshop.service.Basket;
import cz.fi.muni.pv243.eshop.service.ProductManager;

/**
 * @author Matous Jobanek
 */
@Model
public class BasketController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Basket basket;

	private ProductInBasket productInBasket;

	private ProductToBasket productToBasket;

	private ProductUpdateBasket productUpdateBasket;
	private static Long basketPrice;
	@Inject
	private ProductManager productManager;

	public Long getBasketPrice() {
		return basketPrice;
	}

	@Produces
	@Named
	public ProductInBasket getProductInBasket() {
		return productInBasket;
	}

	@Produces
	@Named
	public ProductToBasket getProductToBasket() {
		return productToBasket;
	}

	@Produces
	@Named
	public ProductUpdateBasket getProductUpdateBasket() {
		return productUpdateBasket;
	}

	@PostConstruct
	public void initNewProduct() {
		productInBasket = new ProductInBasket();
		productToBasket = new ProductToBasket(null, 1);
		productUpdateBasket = new ProductUpdateBasket();
	}

	public void addProductToBasket() throws Exception {
		basket.addProduct(
				Long.parseLong(productToBasket.getProductId().getValue()
						.toString()), productToBasket.getQuantity());
		productToBasket = new ProductToBasket(null, 1);
	}

	public void updateProductInBasket() throws Exception {
		basket.updateProduct(
				Long.parseLong(productUpdateBasket.getProductId().getValue()
						.toString()),

				productUpdateBasket.getQuantity());
		productUpdateBasket = new ProductUpdateBasket(null, 0);
	}

	@Produces
	@Named("productsInBasket")
	public List<ProductInBasket> getProducts() throws Exception {
		List<OrderLine> lines = new ArrayList<OrderLine>();
		Map<Long, Integer> toOrder = basket.getBasketContent();
		basketPrice = 0L;
		for (Long key : toOrder.keySet()) { // Need to rewrite whole basket in
											// order not to still look to DB
			Product productToAdd = productManager.findProduct(key);
			int quantity = toOrder.get(key);
			lines.add(new OrderLine(productToAdd, quantity));
			basketPrice += (productToAdd.getPrice() * quantity);
		}
		return basket.getAllMessages();
	}

	public boolean isBasketEmpty() {
		return basket.isEmpty();
	}

}
