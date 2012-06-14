package cz.fi.muni.pv243.eshop.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.fi.muni.pv243.eshop.model.Order;

@Named("orderManager")
@Stateless
public class OrderManagerImpl implements OrderManager {

	@Inject
	private transient Logger logger;

	@Inject
	private EntityManager orderDatabase;

	@Inject
	private Event<Order> orderEventSrc;

	@Override
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	@RequestScoped
	public List<Order> getOrders() {
		return orderDatabase.createQuery("select o from Order o")
				.getResultList();
	}

	@Override
	public void addOrder(Order order) {
		orderDatabase.persist(order);
		logger.info("Adding " + order.toString());
		orderEventSrc.fire(order);

	}

}
