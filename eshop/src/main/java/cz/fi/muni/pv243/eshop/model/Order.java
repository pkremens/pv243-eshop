package cz.fi.muni.pv243.eshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order {
	@Id
	@GeneratedValue
	private Long id;

	// private Customer customer;
	// private List<Product> products;
	//
	// /**
	// * @return the id
	// */
	// public Long getId() {
	// return id;
	// }
	//
	// /**
	// * @param id
	// * the id to set
	// */
	// public void setId(Long id) {
	// this.id = id;
	// }
	//
	// /**
	// * @return the customer
	// */
	// public Customer getCustomer() {
	// return customer;
	// }
	//
	// /**
	// * @param customer
	// * the customer to set
	// */
	// public void setCustomer(Customer customer) {
	// this.customer = customer;
	// }
	//
	// /**
	// * @return the product
	// */
	// public List<Product> getProduct() {
	// return products;
	// }
	//
	// /**
	// * @param product
	// * the product to set
	// */
	// public void setProduct(List<Product> products) {
	// this.products = products;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see java.lang.Object#hashCode()
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result
	// + ((customer == null) ? 0 : customer.hashCode());
	// result = prime * result + ((id == null) ? 0 : id.hashCode());
	// result = prime * result
	// + ((products == null) ? 0 : products.hashCode());
	// return result;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see java.lang.Object#equals(java.lang.Object)
	// */
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// Order other = (Order) obj;
	// if (customer == null) {
	// if (other.customer != null)
	// return false;
	// } else if (!customer.equals(other.customer))
	// return false;
	// if (id == null) {
	// if (other.id != null)
	// return false;
	// } else if (!id.equals(other.id))
	// return false;
	// if (products == null) {
	// if (other.products != null)
	// return false;
	// } else if (!products.equals(other.products))
	// return false;
	// return true;
	// }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
