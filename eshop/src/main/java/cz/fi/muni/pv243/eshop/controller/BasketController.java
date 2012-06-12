package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.model.ProductInBasket;

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

	@Produces
	@Named
	public ProductInBasket getProductInBasket() {
		return productInBasket;
	}

	@PostConstruct
	public void initNewProduct() {
		productInBasket = new ProductInBasket();
	}

	public void addProductToBasket(Product product) throws Exception {
		basket.addProduct(productInBasket.getProduct(),
				productInBasket.getQuantity());
	}

}
