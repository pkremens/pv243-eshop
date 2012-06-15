package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.OrderLine;

public interface OrderLineManager {
	List<OrderLine> getOrderLines();

	void addOrderLine(OrderLine orderLine);

}
