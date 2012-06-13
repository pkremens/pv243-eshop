package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.Customer;

public interface CustomerManager {

	public List<Customer> getCustomers() throws Exception;

	public void addCustomer(Customer customer) throws Exception;

	public Customer findCustomer(String username, String passwordCredential);

	// public Customer getNewCustomer();

	public Customer isRegistred(String email);
	
	public String sha2(String password, Integer salt);

}