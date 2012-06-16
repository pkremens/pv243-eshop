package cz.fi.muni.pv243.eshop.controller;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.service.CustomerManager;
import cz.fi.muni.pv243.eshop.service.Security;

public class Authenticator extends BaseAuthenticator {
	@Inject
	private Credentials credentials;

	@Inject
	private FacesContext facesContext;

	@Inject
	private CustomerManager customerManager;

	@Inject
	Logger log;

	@Override
	public void authenticate() {
		if (credentials.getUsername().equals("")) {
			setStatus(AuthenticationStatus.FAILURE);
			log.finest("Authentication: username cannot be empty");
			facesContext.addMessage("loginForm:username", new FacesMessage(
					"Cannot enter empry username"));
		} else {
			Customer customer = customerManager.isRegistred(credentials
					.getUsername());
			if (customer == null) {
				setStatus(AuthenticationStatus.FAILURE);
				facesContext.addMessage("loginForm:username", new FacesMessage(
						"Non existing user"));

				log.finest("Authentication: Non existing user");
			} else {
				String saltPart = customer.getPassword().split("\\$")[0];
				Integer salt = Integer.parseInt(saltPart, 16);

				String password = Security.sha2(
						((PasswordCredential) credentials.getCredential())
								.getValue(), salt);

				if (customer.getPassword().equals(password)) {
					setStatus(AuthenticationStatus.SUCCESS);
					setUser(customer);
					log.finest("Authentication: " + customer.toString());
					// FacesContext.getCurrentInstance().addMessage(
					// "loginForm:loginButton",
					// new FacesMessage("Welcome, " + customer.getName()));

				} else {
					setStatus(AuthenticationStatus.FAILURE);
					log.warning("Authentication: wrong password for user "
							+ customer.getEmail());
					FacesContext.getCurrentInstance().addMessage(
							"loginForm:password",
							new FacesMessage("Wrong Password"));

				}
			}
		}
	}
}
