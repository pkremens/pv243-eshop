package cz.fi.muni.pv243.eshop.service;

import java.util.List;
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

	private final Customer newCustomer = new Customer();

	@Override
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	@RequestScoped
	public List<Customer> getCustomers() throws Exception {
		return customerDatabase.createQuery("select c from Customer c")
				.getResultList();
	}

	@Override
	public String addCustomer() throws Exception {
		customerDatabase.persist(newCustomer);
		logger.info("Added " + newCustomer);
		return "userAdded";
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

	@Override
	@Produces
	@RequestScoped
	@Named
	public Customer getNewCustomer() {
		return newCustomer;
	}

	@Override
	public List<String> geCustomerNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
