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
import javax.persistence.criteria.Root;

import cz.fi.muni.pv243.eshop.model.Customer;

@RequestScoped
public class CustomerListProducer {
	@Inject
	private EntityManager em;

	private List<Customer> customers;

	// @Named provides access the return value via the EL variable name
	// "products" in the UI (e.g.,
	// Facelets or JSP view)
	@Produces
	@Named("ascCustomers")
	public List<Customer> getCustomers() {
		return customers;
	}

	public void onCustomerListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Customer customer) {
		retrieveAllCustomersOrderedByEmail();
	}

	@PostConstruct
	public void retrieveAllCustomersOrderedByEmail() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
		Root<Customer> customer = criteria.from(Customer.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		// TODO jen visible=true
		criteria.select(customer).orderBy(cb.asc(customer.get("email")));
		customers = em.createQuery(criteria).getResultList();

	}
}
