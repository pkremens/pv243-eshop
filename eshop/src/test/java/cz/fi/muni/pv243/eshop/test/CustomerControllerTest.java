/**
 * 
 */
package cz.fi.muni.pv243.eshop.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
	public static JavaArchive createDeployment() {
		System.err.println("set test");
		JavaArchive webArch = ShrinkWrap
				.create(JavaArchive.class)
				.addClasses(CustomerBean.class, CustomerManager.class,
						CustomerManagerImpl.class, Customer.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		// .addAsResource("META-INF/persistence.xml");
		// .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
		// .setWebXML(new File("WEB-INF/web.xml"));
		// .addAsWebInfResource(new File("WEB-INF/faces-config.xml"));
		// .addAsWebInfResource(new File("WEB-INF/eshop-ds.xml"));
		System.err.println(webArch.toString(true));
		return webArch;

	}

	@Inject
	CustomerBean customerController;

	@Inject
	Logger log;

	@Test
	public void testRegister() throws Exception {
		System.err.println("run test");
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
