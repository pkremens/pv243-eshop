package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.Orders;

public interface OrderManager {

	List<Orders> getOrders();

	void addOrder(Orders orders);

	// List<Orders> getCustomerOrders(String email);

	Orders getOrderDetails(Long id);
	
	void closeOrder(Long id);

}
