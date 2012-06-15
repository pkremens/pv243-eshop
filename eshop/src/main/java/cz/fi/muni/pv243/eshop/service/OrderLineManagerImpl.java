package cz.fi.muni.pv243.eshop.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.fi.muni.pv243.eshop.model.OrderLine;

@Named("orderLineManager")
@Stateless
public class OrderLineManagerImpl implements OrderLineManager {

	@Inject
	private transient Logger logger;

	@Inject
	private EntityManager orderLineDatabase;

	@Override
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	@RequestScoped
	public List<OrderLine> getOrderLines() {
		return orderLineDatabase.createQuery("select o from OrderLine o")
				.getResultList();
	}

	@Override
	public void addOrderLine(OrderLine orderLine) {
		orderLineDatabase.persist(orderLine);
		logger.finest("Adding " + orderLine.toString());

	}

}
