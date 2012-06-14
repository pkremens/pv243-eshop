package cz.fi.muni.pv243.eshop.data;

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
import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.service.CustomerManager;

@RequestScoped
public class OrderListProducer {
	@Inject
	private EntityManager em;

	private List<Orders> orders;

	@Inject
	private Identity identity;

	@Inject
	private CustomerManager customerManager;
	
	@Produces
	@Named("customerOrders")
	public List<Orders> getOrders() {
		return orders;
	}

	public void onProductListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Product product) {
		retrieveAllCustomersOrders();
	}

	@PostConstruct
	public void retrieveAllCustomersOrders() {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<Orders> criteria = cb.createQuery(Orders.class);
		 Root<Orders> orders = criteria.from(Orders.class);
		 // Swap criteria statements if you would like to try out type-safe
		 // criteria queries, a new
		 // feature in JPA 2.0
		 // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		 // TODO jen customer = identity.customer
		 
		 Customer customer = customerManager.isRegistred(identity.getUser().toString());
		 
		 criteria.select(orders).orderBy(cb.asc(orders.get("id")));
		 
		 Expression<Customer> customerExpression = orders.get("customer");
		 
		 this.orders = em.createQuery(criteria.where(cb.equal(customerExpression, customer)))
		 .getResultList();

	}
}
