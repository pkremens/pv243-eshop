package cz.fi.muni.pv243.eshop.service;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.fi.muni.pv243.eshop.model.Customer;

@Named("userManager")
@RequestScoped
@Stateful
public class CustomerManagerImpl implements CustomerManager {

	@Inject
	private transient Logger logger;

	@Inject
	private EntityManager customerDatabase;

	// private final Customer newCustomer = new Customer();

	// @Inject
	// private Event<Customer> customerEventSrc;

	@Override
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	@RequestScoped
	public List<Customer> getCustomers() {
		return customerDatabase.createQuery("select c from Customer c")
				.getResultList();
	}

	@Override
	public void addCustomer(Customer customer) {
		try {
			Random r = new Random(System.currentTimeMillis());
			Integer salt = r.nextInt(Integer.MAX_VALUE - 10);
			customer.setPassword(Security.sha2(customer.getPassword(), salt));
		} catch (Exception e) {
			System.err.println("Error creating hash of password");
		}

		customerDatabase.persist(customer);
		logger.info("Adding " + customer.toString());

	}

	@Override
	public Customer findCustomer(String email, String passwordCredential) {
		try {
			Customer customer = (Customer) customerDatabase
					.createQuery(
							"select c from Customer c where c.email=:email and c.password=:password")
					.setParameter("email", email)
					.setParameter("password", passwordCredential)
					.getSingleResult();
			return customer;
		} catch (Exception ex) {
			return null;
		}
	}

	// @Override
	// @Produces
	// @RequestScoped
	// @Named
	// public Customer getNewCustomer() {
	// return newCustomer;
	// }

	@Override
	public Customer isRegistred(String email) {
		try {
			Customer c = (Customer) customerDatabase
					.createQuery(
							"select c from Customer c where c.email=:email")
					.setParameter("email", email).getSingleResult();
			return c;
		} catch (Exception ex) {
			return null;
		}
	}

}
