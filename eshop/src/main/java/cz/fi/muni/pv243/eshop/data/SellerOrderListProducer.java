package cz.fi.muni.pv243.eshop.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Orders;
import cz.fi.muni.pv243.eshop.service.OrderManager;

@RequestScoped
public class SellerOrderListProducer {
	@Inject
	private OrderManager orderManager;

	private List<Orders> activeOrders;
	private List<Orders> closedOrders;

	@Produces
	@Named("allActiveOrders")
	public List<Orders> getActiveOrders() {
		return activeOrders;
	}

	@Produces
	@Named("allClosedOrders")
	public List<Orders> getClosedOrders() {
		return closedOrders;
	}

	public void onOrderListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Orders orders) {
		retrieveAllOrders();
	}

	@PostConstruct
	public void retrieveAllOrders() {
		activeOrders = orderManager.getActiveOrders();
		closedOrders = orderManager.getClosedOrders();

	}
}
