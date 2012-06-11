package cz.fi.muni.pv243.eshop.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.fi.muni.pv243.eshop.model.Product;

@Named("productManager")
@RequestScoped
@Stateful
public class ProductManagerImpl implements ProductManager {

	@Inject
	private transient Logger logger;

	@Inject
	private EntityManager productDatabase;

	@Inject
	private Event<Product> productEventSrc;

	private final Product newProduct = new Product();

	@Override
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	@RequestScoped
	public List<Product> getProducts() throws Exception {
		return productDatabase.createQuery("select u from Product u")
				.getResultList();
	}

	@Override
	public void addProduct(Product product) throws Exception {
		logger.info("Adding " + product.getName());
		productDatabase.persist(product);
		productEventSrc.fire(product);
	}

	// public void register(Product product) throws Exception {
	// logger.info("Adding " + product.getName());
	// productDatabase.persist(product);
	// productEventSrc.fire(product);
	// }

	@Override
	public Product findProduct(long id) throws Exception {
		@SuppressWarnings("unchecked")
		List<Product> results = productDatabase
				.createQuery("select u from Product p where p.id=:id")
				.setParameter("id", id).getResultList();
		if (results.isEmpty()) {
			return null;
		} else if (results.size() > 1) {
			throw new IllegalStateException(
					"Cannot have more than one product with the same email!");
		} else {
			return results.get(0);
		}
	}

	// @Override
	// @Produces
	// @RequestScoped
	// @Named
	// public Product getNewProduct() {
	// return newProduct;
	// }

}
