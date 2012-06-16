package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.service.ProductManager;

@Named("productsBean")
@SessionScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ProductManager productManager;

	private static List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void saveAction(Product product) {
		product.setEditable(false);
		productManager.update(product);
	}

	public void editAction(Product product) {
		product.setEditable(true);
	}

	@PostConstruct
	public void retrieveAllProducts() {
		productList = productManager.getProducts();

	}

}
