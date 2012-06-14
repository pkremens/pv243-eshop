package cz.fi.muni.pv243.eshop.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.service.CustomerManager;

@Model
public class CustomerController {
	@Inject
	private FacesContext facesContext;

	@Inject
	private CustomerManager customerManager;

	@Inject
	private Logger log;

	private Customer newCustomer;

	@Produces
	@Named
	public Customer getNewCustomer() {
		return newCustomer;
	}

	public void register() throws Exception {
		if (newCustomer.getPassword() == null) {
			facesContext.addMessage("addForm:password", new FacesMessage(
					"Cannot have empty password"));
			log.finest("Registration: empty password");
			initNewCustomer();
		} else {
			Customer customer = customerManager.isRegistred(newCustomer
					.getEmail());
			if (customer == null) {
				customerManager.addCustomer(newCustomer);
				log.info("Registration: adding new customer");
				facesContext.addMessage("addForm:registerButton",
						new FacesMessage("Customer was added"));
				initNewCustomer();
			} else {
				log.info("Registration: trying to use already registred email");
				facesContext.addMessage("addForm:registerButton",
						new FacesMessage("User is already registered"));
				initNewCustomer();
			}

		}
	}

	@PostConstruct
	public void initNewCustomer() {
		newCustomer = new Customer();
	}

}
