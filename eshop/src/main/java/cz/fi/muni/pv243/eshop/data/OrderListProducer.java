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

import cz.fi.muni.pv243.eshop.model.Orders;
import cz.fi.muni.pv243.eshop.model.Product;

@RequestScoped
public class OrderListProducer {
	@Inject
	private EntityManager em;

	private List<Orders> orders;

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
		// CriteriaBuilder cb = em.getCriteriaBuilder();
		// CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
		// Root<Product> product = criteria.from(Product.class);
		// // Swap criteria statements if you would like to try out type-safe
		// // criteria queries, a new
		// // feature in JPA 2.0
		// // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		// // TODO jen customer = identity.customer
		// criteria.select(product).orderBy(cb.asc(product.get("id")));
		// Expression<Boolean> isVisible = product.get("visible");
		// orders = em.createQuery(criteria.where(cb.isTrue(isVisible)))
		// .getResultList();

	}
}
