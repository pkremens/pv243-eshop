package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.model.OrderLine;
import cz.fi.muni.pv243.eshop.model.Orders;
import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.service.Basket;
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
	private OrderManager orderManager;

	@Inject
	private ProductManager productManager;

	@Inject
	private transient Logger logger;

	private Customer customer;

	private Orders newOrder;
	private HtmlOutputText orderId;

	private Orders orders;

	@Produces
	@Named
	public Orders getNewOrder() {
		return newOrder;
	}

	@Produces
	@Named
	public HtmlOutputText getOrderId() {
		return orderId;
	}

	// // TODO delete, it's only a dummy method!
	// public void register() throws Exception {
	// System.out.println("ahoj");
	// System.out.println(identity.getUser());
	// customer = (Customer) identity.getUser();
	// newOrder.setCustomer(customer);
	// System.out.println(newOrder);
	// orderManager.addOrder(newOrder);
	// facesContext.addMessage("testForm:testButton", new FacesMessage(
	// FacesMessage.SEVERITY_INFO, "Added!", "Order was added"));
	// initNewOrder();
	// }

	public void makeOrder() {
		if (!basket.isEmpty()) {

			logger.fine("Making new order");
			customer = (Customer) identity.getUser();
			newOrder.setOpen(true);
			newOrder.setCustomer(customer); // set here to allow customer fill
											// the form and then register
			newOrder.setCreationDate(Calendar.getInstance().getTime());

			List<OrderLine> lines = new ArrayList<OrderLine>();
			Map<Long, Integer> toOrder = basket.getBasketContent();
			logger.info("customer " + customer.toLog()
					+ " is ordering following products:");
			Long price = 0L;
			for (Long key : toOrder.keySet()) {
				Product productToAdd = productManager.findProduct(key);
				int quantity = toOrder.get(key);
				lines.add(new OrderLine(productToAdd, quantity));
				logger.info(productToAdd.toString() + " quantity: " + quantity);
				price += (productToAdd.getPrice() * quantity);
			}
			newOrder.setTotalPrice(price);
			newOrder.setOrderLines(lines);
			orderManager.addOrder(newOrder);
			basket.initNewBasket();
			initNewOrder();

		}
	}

	public void getOrderDetail() {
		long parsedId = Long.parseLong(orderId.getValue().toString());
		orders = orderManager.getOrderDetails(parsedId);
	}
	
	
	public void closeOrder() {
		long parsedId = Long.parseLong(orderId.getValue().toString());
		orderManager.closeOrder(parsedId);
	}

	@Produces
	@Named("renderOrderDetail")
	public Orders renderOrderDetail() throws Exception {
		return orders;
	}

	@PostConstruct
	public void initNewOrder() {
		newOrder = new Orders();
		orderId = new HtmlOutputText();
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(HtmlOutputText orderId) {
		this.orderId = orderId;
	}

}
