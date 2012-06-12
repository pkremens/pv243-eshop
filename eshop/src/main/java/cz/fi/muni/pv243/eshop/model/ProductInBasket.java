package cz.fi.muni.pv243.eshop.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author Matous Jobanek
 */
public class ProductInBasket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8804613372092869262L;

	@NotNull
	private Product product;

	@NotNull
	private int quantity;

	/**
	 * 
	 */
	public ProductInBasket() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param product
	 * @param quantity
	 */
	public ProductInBasket(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		ProductInBasket other = (ProductInBasket) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
