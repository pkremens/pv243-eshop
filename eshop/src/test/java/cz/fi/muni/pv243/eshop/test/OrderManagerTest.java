/**
 * 
 */
package cz.fi.muni.pv243.eshop.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.fi.muni.pv243.eshop.data.OrderListProducer;
import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.model.OrderLine;
import cz.fi.muni.pv243.eshop.model.Orders;
import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.service.CustomerManager;
import cz.fi.muni.pv243.eshop.service.CustomerManagerImpl;
import cz.fi.muni.pv243.eshop.service.OrderManager;
import cz.fi.muni.pv243.eshop.service.OrderManagerImpl;
import cz.fi.muni.pv243.eshop.service.ProductManager;
import cz.fi.muni.pv243.eshop.service.ProductManagerImpl;
import cz.fi.muni.pv243.eshop.service.Security;
import cz.fi.muni.pv243.eshop.util.Resources;

/**
 * @author Matous Jobanek
 * 
 */
@RunWith(Arquillian.class)
public class OrderManagerTest {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive webArch = ShrinkWrap
				.create(WebArchive.class)
				.addClasses(OrderManager.class, OrderManagerImpl.class,
						Orders.class, Customer.class, Resources.class,
						CustomerManager.class, CustomerManagerImpl.class,
						Resources.class, OrderLine.class, Product.class,
						Security.class, OrderListProducer.class,
						ProductManager.class, ProductManagerImpl.class)
				.addPackage(org.picketlink.idm.api.User.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource("jbossas-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		return webArch;

	}

	@Inject
	private OrderManager orderManager;

	@Inject
	CustomerManager customerManager;

	@Inject
	private ProductManager productManager;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		addCustomer();
		productManager.addProduct(getProduct());
	}

	private void addCustomer() throws Exception {
		Customer customer = getCustomer();
		customer.setEmail("test@test.cz");
		customer.setName("test");
		customer.setPassword("testPasswd");
		customer.setRole("user");
		customerManager.addCustomer(customer);
	}

	private Product getProduct() {
		Product product = new Product();
		product.setName("test");
		product.setPrice(4321);
		product.setVisible(true);
		product.setEditable(true);
		return product;
	}

	/**
	 * Test method for
	 * {@link cz.fi.muni.pv243.eshop.controller.OrderController#makeOrder()}.
	 */
	@Test
	public void testMakeOrder() {
		Orders orders = new Orders(getCustomer());
		orders.setCreationDate(new Date());
		orders.setOpen(true);
		orders.setOrderLines(getOrderLines());
		orders.setTotalPrice(new Long(123456));

		orderManager.addOrder(orders);

		List<Orders> returnedOrders = orderManager.getOrders();
		if (returnedOrders.size() == 0) {
			fail("there is no order");
		}
		Orders orderDetails = orderManager.getOrderDetails(returnedOrders
				.get(0).getId());
		Assert.assertNotNull(orderDetails);
		Assert.assertNotNull(orderDetails.getCreationDate());
		Assert.assertNotNull(orderDetails.getCustomer());
		Assert.assertNotNull(orderDetails.getId());
		Assert.assertNotNull(orderDetails.getOrderLines());
		Assert.assertNotNull(orderDetails.getTotalPrice());
		Assert.assertNotNull(orderDetails.isOpen());
		Assert.assertTrue(orderDetails.isOpen());

		orderManager.closeOrder(returnedOrders.get(0).getId());
		orderDetails = orderManager.getOrderDetails(returnedOrders.get(0)
				.getId());
		Assert.assertFalse(orderDetails.isOpen());
	}

	private List<OrderLine> getOrderLines() {
		OrderLine orderLine = new OrderLine();
		orderLine.setQuantity(8);
		orderLine.setProduct(getProduct());
		ArrayList<OrderLine> lines = new ArrayList<OrderLine>();
		lines.add(orderLine);
		return lines;
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setEmail("test@test.cz");
		customer.setName("test");
		customer.setPassword("testPasswd");
		customer.setRole("user");
		return customer;
	}
}
