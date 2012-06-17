/**
 * 
 */
package cz.fi.muni.pv243.eshop.test;

import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.service.CustomerManager;
import cz.fi.muni.pv243.eshop.service.CustomerManagerImpl;
import cz.fi.muni.pv243.eshop.service.Security;
import cz.fi.muni.pv243.eshop.util.Resources;

/**
 * @author Matous Jobanek
 * 
 */
@RunWith(Arquillian.class)
public class CustomerManagerTest {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive webArch = ShrinkWrap
				.create(WebArchive.class)
				.addClasses(Customer.class, Resources.class,
						CustomerManager.class, CustomerManagerImpl.class,
						Security.class)
				.addPackage(org.picketlink.idm.api.User.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource("jbossas-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		return webArch;

	}

	@Inject
	CustomerManager customerManager;

	/**
	 * Test method for
	 * {@link cz.fi.muni.pv243.eshop.controller.CustomerBean#register()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegister() throws Exception {
		Customer customer = new Customer();
		customer.setEmail("test@test.cz");
		customer.setName("test");
		customer.setPassword("testPasswd");
		customer.setRole("user");
		customerManager.addCustomer(customer);

		List<Customer> customerList = customerManager.getCustomers();
		if (customerList.size() == 0) {
			fail("there is no customer");
		}

		Assert.assertNotNull(customerList.get(0));
		Assert.assertNotNull(customerList.get(0).getEmail());
		Assert.assertNotNull(customerList.get(0).getName());
		Assert.assertNotNull(customerList.get(0).getPassword());
		Assert.assertNotNull(customerList.get(0).getRole());

		Assert.assertEquals(customerList.get(0).getEmail(), "test@test.cz");
		Assert.assertEquals(customerList.get(0).getName(), "test");
		Assert.assertEquals(customerList.get(0).getRole(), "user");
	}
}
