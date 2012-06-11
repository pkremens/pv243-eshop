package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.Product;

public interface ProductManager {

	public List<Product> getProducts() throws Exception;

	public String addProduct() throws Exception;

	public Product findProduct(long id) throws Exception;

	public Product getNewProduct();

}