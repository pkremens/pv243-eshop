package cz.fi.muni.pv243.eshop.data;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.jboss.seam.security.Identity;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.model.Orders;

@RequestScoped
public class OrderListProducer {
	@Inject
	private EntityManager em;

	private List<Orders> activeOrders;
	private List<Orders> closedOrders;

	@Inject
	private Identity identity;

	@Produces
	@Named("customerActiveOrders")
	public List<Orders> getActiveOrders() {
		return activeOrders;
	}

	@Produces
	@Named("customerClosedOrders")
	public List<Orders> getClosedOrders() {
		return closedOrders;
	}

	public void onOrderListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Orders orders) {
		retrieveAllCustomersOrders();
	}

	@PostConstruct
	public void retrieveAllCustomersOrders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Orders> criteria = cb.createQuery(Orders.class);
		Root<Orders> orders = criteria.from(Orders.class);

		Customer customer = (Customer) identity.getUser();

		criteria.select(orders).orderBy(cb.asc(orders.get("id")));
		Expression<Customer> customerExpression = orders.get("customer");
		List<Orders> allOrders = em.createQuery(
				criteria.where(cb.equal(customerExpression, customer)))
				.getResultList();
		activeOrders = new ArrayList<Orders>();
		closedOrders = new ArrayList<Orders>();
		for (Orders order : allOrders) {
			if (order.isOpen()) {
				activeOrders.add(order);
			} else {
				closedOrders.add(order);
			}
		}
	}
}
