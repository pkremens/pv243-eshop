package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.model.Orders;
import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.service.Basket;
import cz.fi.muni.pv243.eshop.service.CustomerManager;
import cz.fi.muni.pv243.eshop.service.OrderManager;
import cz.fi.muni.pv243.eshop.service.ProductManager;

@Model
public class OrderController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Identity identity;

	@Inject
	private FacesContext facesContext;

	@Inject
	Basket basket;

	@Inject
	private CustomerManager customerManager;
	@Inject
	private OrderManager orderManager;
	@Inject
	private ProductManager productManager;

	private Customer customer;
	private Orders newOrder;

	@Produces
	@Named
	public Orders getNewOrder() {
		return newOrder;
	}

	// TODO delete, it's only a dummy method!
	public void register() throws Exception {
		System.out.println("ahoj");
		System.out.println(identity.getUser());
		customer = (Customer) identity.getUser();
		newOrder.setCustomer(customer);
		System.out.println(newOrder);
		orderManager.addOrder(newOrder);
		facesContext.addMessage("testForm:testButton", new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Added!", "Order was added"));
		initNewOrder();
	}

	public void makeOrder() {
		if (!basket.isEmpty()) {

			System.out.println("Making order");
			customer = (Customer) identity.getUser();
			newOrder.setCustomer(customer);
			Set<Product> products = new HashSet<Product>();

			HashMap<Long, Integer> toOrder = basket.getBasketContent();
			for (Long key : toOrder.keySet()) {
				products.add(productManager.findProduct(key));
				System.out.println(productManager.findProduct(key));
			}

			newOrder.setProducts(products);
			orderManager.addOrder(newOrder);
			initNewOrder();

		}
	}

	@PostConstruct
	public void initNewOrder() {
		newOrder = new Orders();
	}

}
