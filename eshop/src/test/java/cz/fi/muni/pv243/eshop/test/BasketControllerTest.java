/**
 * 
 */
package cz.fi.muni.pv243.eshop.test;

import static org.junit.Assert.fail;

import java.util.List;

import javax.faces.component.html.HtmlOutputText;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.fi.muni.pv243.eshop.controller.BasketController;
import cz.fi.muni.pv243.eshop.model.OrderLine;
import cz.fi.muni.pv243.eshop.model.Product;
import cz.fi.muni.pv243.eshop.model.ProductInBasket;
import cz.fi.muni.pv243.eshop.model.ProductToBasket;
import cz.fi.muni.pv243.eshop.model.ProductUpdateBasket;
import cz.fi.muni.pv243.eshop.service.Basket;
import cz.fi.muni.pv243.eshop.service.BasketBean;
import cz.fi.muni.pv243.eshop.service.ProductManager;
import cz.fi.muni.pv243.eshop.service.ProductManagerImpl;
import cz.fi.muni.pv243.eshop.util.Resources;

/**
 * @author Matous Jobanek
 * 
 */
@RunWith(Arquillian.class)
public class BasketControllerTest {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive webArch = ShrinkWrap
				.create(WebArchive.class)
				.addClasses(ProductInBasket.class, ProductToBasket.class,
						ProductUpdateBasket.class, ProductInBasket.class,
						BasketBean.class, BasketController.class, Basket.class,
						ProductManager.class, ProductManagerImpl.class,
						Product.class, Resources.class, OrderLine.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource("jbossas-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return webArch;

	}

	@Inject
	private BasketController basketController;

	@Inject
	private ProductManager productManager;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		basketController.initNewProduct();
		productManager.addProduct(getProduct());
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
	 * {@link cz.fi.muni.pv243.eshop.service.BasketBean#addProduct(java.lang.Long, int)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddProduct() throws Exception {
		basketController.initNewProduct();
		HtmlOutputText htmlOutputText = new HtmlOutputText();
		htmlOutputText.setValue(productManager.getProducts().get(0).getId());
		basketController.getProductToBasket().setProductId(htmlOutputText);
		basketController.getProductToBasket().setQuantity(4321);
		basketController.addProductToBasket();
		List<ProductInBasket> products = basketController.getProducts();
		if (products.size() == 0 || basketController.isBasketEmpty()) {
			fail("there is no product");
		}
	}
}
