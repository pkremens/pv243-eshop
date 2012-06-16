/**
 * 
 */
package cz.fi.muni.pv243.eshop.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.fi.muni.pv243.eshop.controller.CustomerBean;
import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.service.CustomerManager;
import cz.fi.muni.pv243.eshop.service.CustomerManagerImpl;

/**
 * @author Matous Jobanek
 * 
 */
@RunWith(Arquillian.class)
public class CustomerControllerTest {

	@Deployment
	public static WebArchive createTestArchive() {
		System.err.println("test");
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(CustomerBean.class, CustomerManager.class,
						CustomerManagerImpl.class, Customer.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.setWebXML(new File("WEB-INF/web.xml"))
				.addAsWebInfResource(new File("WEB-INF/faces-config.xml"))
				.addAsWebInfResource(new File("WEB-INF/eshop-ds.xml"));

	}

	@Inject
	CustomerBean customerController;

	@Inject
	Logger log;

	@Test
	public void testRegister() throws Exception {
		System.err.println("test");
		customerController.initNewCustomer();
		customerController.getNewCustomer().setName("Jane Doe");
		customerController.getNewCustomer().setEmail("jane@mailinator.com");
		customerController.getNewCustomer().setPassword("2125551234");
		customerController.getNewCustomer().setRole("user");

		customerController.register();
		assertNotNull(customerController.getNewCustomer().getId());
		log.info(customerController.getNewCustomer().getName()
				+ " was persisted with id "
				+ customerController.getNewCustomer().getId());
	}

}
