package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.Orders;

public interface OrderManager {

	List<Orders> getOrders();

	List<Orders> getActiveOrders();

	List<Orders> getClosedOrders();

	void addOrder(Orders orders);

	Orders getOrderDetails(Long id);

	void closeOrder(Long id);

}
