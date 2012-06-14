package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.model.Order;

public interface OrderManager {

	public List<Order> getOrders();

	public void addOrder(Order order);

	public Customer getCustomerOrders(String email);

}
