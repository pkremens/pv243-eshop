package cz.fi.muni.pv243.eshop.model;

import java.io.Serializable;

import javax.faces.component.html.HtmlOutputText;
import javax.validation.constraints.NotNull;

/**
 * @author Matous Jobanek
 */
public class ProductToBasket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8804613372092869262L;

	@NotNull
	private int quantity;
	@NotNull
	private HtmlOutputText productId;

	/**
	 * 
	 */
	public ProductToBasket() {
		super();
	}

	/**
	 * @param quantity
	 * @param productId
	 */
	public ProductToBasket(HtmlOutputText productId, int quantity) {
		super();
		this.quantity = quantity;
		this.productId = productId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the productId
	 */
	public HtmlOutputText getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(HtmlOutputText productId) {
		this.productId = productId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + quantity;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductToBasket other = (ProductToBasket) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
