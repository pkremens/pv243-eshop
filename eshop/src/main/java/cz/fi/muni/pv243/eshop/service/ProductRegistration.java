package cz.fi.muni.pv243.eshop.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import cz.fi.muni.pv243.eshop.model.Product;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class ProductRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Product> productEventSrc;

	public void register(Product product) throws Exception {
		log.info("Registering " + product.getName());
		em.persist(product);
		productEventSrc.fire(product);
	}
}
