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

public class Authenticator extends BaseAuthenticator {
	@Inject
	Credentials credentials;

	@Inject
	EntityManager em;

	@Inject
	private CustomerManager customerManager;

	@Override
	public void authenticate() {

		Customer customer = customerManager.findCustomer(
				credentials.getUsername(),
				((PasswordCredential) credentials.getCredential()).getValue());

		if (customer != null) {
			setStatus(AuthenticationStatus.SUCCESS);
			setUser(customer);
			System.err.println(customer.toString());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Welcome, " + customer.getName()));

		} else {
			setStatus(AuthenticationStatus.FAILURE);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"Non existing user, or passoword or both :)"));

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
