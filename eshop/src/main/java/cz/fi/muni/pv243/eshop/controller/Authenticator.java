package cz.fi.muni.pv243.eshop.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.service.CustomerManager;
import cz.fi.muni.pv243.eshop.service.Security;

public class Authenticator extends BaseAuthenticator {
	@Inject
	Credentials credentials;

	@Inject
	EntityManager em;

	@Inject
	private CustomerManager customerManager;

	@Override
	public void authenticate() {
		
//		System.err.println("a");
//		for (Customer c: customerManager.getCustomers()) {
//			System.err.println(c);
//		}
//		System.err.println("b");
		
		Customer customer = customerManager.isRegistred(credentials
				.getUsername());
		if (customer == null) {
			setStatus(AuthenticationStatus.FAILURE);
			FacesContext.getCurrentInstance().addMessage("loginForm:username",
					new FacesMessage("Non existing user"));
		} else {
			// customer = customerManager.findCustomer(credentials
			// .getUsername(), ((PasswordCredential) credentials
			// .getCredential()).getValue());

			String saltPart = customer.getPassword().split("\\$")[0];
			Integer salt = Integer.parseInt(saltPart, 16);
			
			String password = Security.sha2(
					((PasswordCredential) credentials.getCredential())
							.getValue(), salt);

			if (customer.getPassword().equals(password)) {
				setStatus(AuthenticationStatus.SUCCESS);
				setUser(customer);
				System.err.println(customer.toString());
				FacesContext.getCurrentInstance().addMessage(
						"loginForm:loginButton",
						new FacesMessage("Welcome, " + customer.getName()));

			} else {
				setStatus(AuthenticationStatus.FAILURE);
				FacesContext.getCurrentInstance().addMessage(
						"loginForm:password",
						new FacesMessage("Wrong Password"));

			}
		}
	}

	// public void logout() {
	// setUser(null);
	// System.err.println("this is called");
	// FacesContext.getCurrentInstance().addMessage(null,
	// new FacesMessage("Goodbye, " + ((Customer)getUser()).getName()));
	//
	// }
	//
	// public boolean isLoggedIn() {
	// return getUser() != null;
	// }
	//
	// @Produces
	// @LoggedIn
	// public Customer getCurrentUser() {
	// return (Customer)getUser();
	// }

}
