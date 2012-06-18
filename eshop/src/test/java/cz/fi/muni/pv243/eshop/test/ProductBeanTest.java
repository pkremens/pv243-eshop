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

import cz.fi.muni.pv243.eshop.data.ProductListProducer;
import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.service.ProductManager;
import cz.fi.muni.pv243.eshop.service.ProductManagerImpl;
import cz.fi.muni.pv243.eshop.util.Resources;

/**
 * @author Matous Jobanek
 * 
 */
@RunWith(Arquillian.class)
public class ProductBeanTest {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive webArch = ShrinkWrap
				.create(WebArchive.class)
				.addClasses(Product.class, ProductManager.class,
						ProductManagerImpl.class, ProductListProducer.class,
						Resources.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource("jbossas-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		return webArch;

	}

	@Inject
	private ProductManager productManager;

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveAction() throws Exception {
		Product product = new Product();
		product.setName("test");
		product.setPrice(4321);
		product.setVisible(true);
		product.setEditable(false);
		productManager.addProduct(product);
		List<Product> products = productManager.getProducts();
		if (products.size() == 0) {
			fail("there is no product");
		}

		Assert.assertNotNull(products.get(0));
		Assert.assertNotNull(products.get(0).getId());
		Assert.assertEquals(products.get(0).getName(), "test");
		Assert.assertEquals(products.get(0).getPrice(), 4321);
		Assert.assertTrue(products.get(0).isVisible());
		Assert.assertFalse(products.get(0).isEditable());

	}
}
