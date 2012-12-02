package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Book;
import cz.fi.muni.pv243.eshop.model.SalableBook;
import cz.fi.muni.pv243.eshop.service.BookEJB;

@Named("bookController")
@ViewScoped
public class BookController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3233569998061759697L;

	@Inject
	private BookEJB bookEJB;

	@Inject
	private Logger log;

	private Book book = new Book();
	private List<Book> bookList = new ArrayList<Book>();
	private List<Book> basket = new ArrayList<Book>();
	private List<SalableBook> provider = new ArrayList<SalableBook>();

	public String doCreateBook() {
		log.info("doCreateBook");
		book = bookEJB.createBook(book);
		bookList.add(book);
		book = new Book();
		return "listBooks.xhtml";
	}

	public Book getBook() {
		log.info("getBook");
		return book;
	}

	public void setBook(Book book) {
		log.info("setBook");
		this.book = book;
	}

	public List<Book> getBookList() {
		log.info("getBookList()");
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		log.info("setBookList");
		this.bookList = bookList;
	}

	public void saveAction(Book book) {
		log.info("BookController.saveAction");
		log.info("Book: " + book.toString());
		book.setEditable(false);
		bookEJB.update(book);
	}

	public void updateSalableBook(SalableBook sb) {
		log.info("Updating Salable book: " + sb.toString());
		bookEJB.updateSalableBook(sb);
	}

	public void editAction(Book book) {
		log.info("Edit in Controller");
		log.info("BookController.editAction");
		book.setEditable(true);
	}

	public void addToBasket(Book book) {
		log.info("BookController.addToBasket");
		book.setInBasket(true);
		// book.setAmount(0L);
		basket.add(book);
	}

	public List<Book> getBooksInBasket() {
		log.info("getBooksInBasket()");
		return basket;
	}

	public boolean isSomethingInBasket() {
		// if (!basket.isEmpty()) {
		// log.info("basket: ");
		// for (Book book : basket) {
		// log.info(book.toString());
		// }
		// }
		// if (!provider.isEmpty()) {
		// log.info("Provider books:");
		// for (SalableBook book : provider) {
		// log.info(book.toString());
		// }
		// }
		return !basket.isEmpty();
	}

	public void clearBasket() {
		if (!basket.isEmpty()) {

			for (Book book : basket) {
				log.info("Removing from basket");
				log.info(book.toString());
				book.setInBasket(false);
				book.setAmount(1L);
			}
			basket.clear();
		} else {
			log.info("Empty basket");
		}

	}

	public void removeFromBasket(Book book) {
		log.info("BookController.removeFromBasket");
		book.setInBasket(false);
		book.setAmount(1L);
		basket.remove(book);
	}

	public void echo() {
		log.info("Echo: Hello");
		retrieveAllBooks();
	}

	public void pritnBasket() {
		log.info("print basket: Is something in basket?: " + !basket.isEmpty());
		if (!basket.isEmpty()) {
			for (Book book : basket) {
				log.info(book.toString());
			}
		}
	}

	public void makeOrder2() {
		log.info("Make order of books");
		// TODO - implement make order
		// fore in basket whether in provider raise amount or put it there with
		// appropriate amount, call clear basket in the end
		if (isSomethingInBasket()) {
			for (Book book : basket) {
				// log.info(book.getAmount().toString());
				// log.info(book.toString());
				boolean onStore = false;
				for (SalableBook sb : provider) {
					if (sb.getBook().equals(book)) {
						onStore = true;
						sb.setAmount(sb.getAmount() + book.getAmount());
						bookEJB.updateSalableBook(sb);
						break;
					}
				}
				if (!onStore) {
					provider.add(bookEJB.createSalableBook(new SalableBook(
							book, book.getAmount())));
				}
			}
			clearBasket();
		}
		// SalableBook salebleBook = new SalableBook(book,
		// book.getAmount());
		// bookEJB.createSalableBook(salebleBook);
		// provider.add(salebleBook);
		// }
		//
		// clearBasket();
		// } else {
		// log.info("Nothing to order");
		// }
	}

	/*
	 * To get books from database immediately after start
	 */
	@PostConstruct
	public void retrieveAllBooks() {
		// Book book = new Book("Ahoj", "desc", "11", 32, false, false);
		// / basket.put(book, 3L);
		log.info("Hello post construct");
		bookList = bookEJB.findBooks();
		for (Book book : bookList) {
			log.info(book.toString());
		}
		setProvider(bookEJB.findSalableBooks());
	}

	public List<Book> getBasket() {
		return basket;
	}

	public void setBasket(List<Book> basket) {
		this.basket = basket;
	}

	public List<SalableBook> getProvider() {
		return provider;
	}

	public void setProvider(List<SalableBook> provider) {
		this.provider = provider;
	}

}
