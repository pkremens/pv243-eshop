package cz.fi.muni.pv243.eshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = "findAllSalableBooks", query = "SELECT b FROM SalableBook b")
public class SalableBook {
	@Id
	@GeneratedValue
	private Long id;
	private int price;
	@OneToOne
	private Book book;
	private boolean active;
	private Long amount;
	@Transient
	private boolean editable;

	public SalableBook() {
		super();
	}

	public SalableBook(Book book, Long amount) {
		this.price = 0;
		this.book = book;
		this.active = false;
		this.amount = amount;
		this.editable = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
