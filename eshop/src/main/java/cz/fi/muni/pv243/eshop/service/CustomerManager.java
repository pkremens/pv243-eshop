package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.Customer;

public interface CustomerManager {

	List<Customer> getCustomers();

	void addCustomer(Customer customer);

	Customer findCustomer(String username, String passwordCredential);

	void update(Customer customer);

	Customer isRegistred(String email);

}