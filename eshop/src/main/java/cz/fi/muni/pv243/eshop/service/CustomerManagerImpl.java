package cz.fi.muni.pv243.eshop.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
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

	@Inject
	private Event<Customer> customerEventSrc;

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
	public void addCustomer(Customer customer) throws Exception {
		try {
			Random r = new Random(System.currentTimeMillis());
			Integer salt = r.nextInt(Integer.MAX_VALUE - 10);
			customer.setPassword(sha2(customer.getPassword(), salt));
		} catch (Exception e) {
			System.err.println("Error creating hash of password");
		}
		
		customerDatabase.persist(newCustomer);
		logger.info("Adding " + customer.toString());
		customerEventSrc.fire(customer);
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

	public String sha2(String password, Integer salt) {
		
		StringBuilder sb = new StringBuilder();
		
		if (Integer.signum(salt) == -1)
			salt *= -1;
		else if (Integer.signum(salt) == 0)
			salt = Integer.MAX_VALUE - 3;
		
		String hex = Integer.toHexString(salt);
		sb.append(hex);
		sb.append("$");
		
		try {
			// compute digest
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] b = md.digest((password + salt).getBytes());

			// output digest
			for (int i = 0; i < b.length; i++) {
				if ((0xff & b[i]) < 0x10) {
					sb.append("0" + Integer.toHexString((0xFF & b[i])));
				} else {
					sb.append(Integer.toHexString(0xFF & b[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// Log.e("ServerAdapter",
			// "Unsupported cryptographic operation, you cannot log in", e);
			System.err
					.println("Unsupported cryptographic operation, you cannot log in");
		}

		return sb.toString();
	}

}
