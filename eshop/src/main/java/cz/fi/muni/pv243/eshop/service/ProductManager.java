package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.Product;

public interface ProductManager {

	List<Product> getProducts();

	void addProduct(Product product) throws Exception;

	Product findProduct(long id);

	void update(Product product);

}