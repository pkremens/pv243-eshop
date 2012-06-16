package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.service.CustomerManager;

@Named("customersBean")
@SessionScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private CustomerManager customerManager;

	private static List<Customer> customerList;

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void updateAction(Customer customer) {
		System.out.println("update");
		customerManager.update(customer);

	}

	@PostConstruct
	public void retrieveAllProducts() {
		customerList = customerManager.getCustomers();

	}

}
