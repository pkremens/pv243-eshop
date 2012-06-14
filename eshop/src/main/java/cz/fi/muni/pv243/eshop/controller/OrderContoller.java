package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Order;
import cz.fi.muni.pv243.eshop.service.OrderManager;

@Model
public class OrderContoller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private OrderManager orderManager;

	private Order newOrder;

	@Produces
	@Named
	public Order getNewOrder() {
		return newOrder;
	}

	public void register() throws Exception {
		orderManager.addOrder(newOrder);
		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Added!", "Order was added"));
		initNewOrder();
	}

	@PostConstruct
	public void initNewOrder() {
		newOrder = new Order();
	}

}
