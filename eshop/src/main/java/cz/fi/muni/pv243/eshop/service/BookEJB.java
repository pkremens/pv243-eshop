package cz.fi.muni.pv243.eshop.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cz.fi.muni.pv243.eshop.model.Book;
import cz.fi.muni.pv243.eshop.model.SalableBook;

@Stateless
public class BookEJB {

	@Inject
	private transient Logger logger;

	@Inject
	private EntityManager em;

	public List<Book> findBooks() {
		TypedQuery<Book> query = em
				.createNamedQuery("findAllBooks", Book.class);
		return query.getResultList();
	}

	public List<SalableBook> findSalableBooks() {
		TypedQuery<SalableBook> query = em.createNamedQuery(
				"findAllSalableBooks", SalableBook.class);
		return query.getResultList();
	}

	public Book createBook(Book book) {
		em.persist(book);
		return book;
	}

	public SalableBook createSalableBook(SalableBook book) {
		em.persist(book);
		return book;
	}

	public void update(Book book) {
		logger.info("updating " + book.toString());
		em.merge(book);
	}

	public void updateSalableBook(SalableBook book) {
		logger.info("updating " + book.toString());
		em.merge(book);
	}

	// vzdycky by mela byt activ POUZE JEDNA polozka se stejnou book (aby se
	// nestalo, ze se prodava jedna kniha zaroven za dve ruzne ceny)
	// vytahnu si result list (pokud by se ukazalo ze je levnejsi pristoupit 2x
	// k db nez tahat kolekci (aj kdyz malou) tak je mozne udelat vice
	// getSingleResult dotazy) a zjistim jestli tam ma active polozku pokud jo
	// tak ji pridam ammount a mergu.
	// Pokud neni zadna aktive polozka tak najdu polozku ktera ma cenu 0
	// (implicitni cena) a udelam update -> merge
	// Pokud bude resultList prazdny tak vytvorim novou polozku s cenou 0,
	// nebude active a nastavim pozadovany pocet kusu

	public void makeOrder(List<Book> basket) {
		for (Book book : basket) {
			logger.info(book.toString());
			// List<SalableBook> books = em
			// .createQuery(
			// "select sb from SalableBook sb where sb.book=:book")
			// .setParameter("book", book).getResultList();
			// if (books.isEmpty()) {
			// createSalableBook(new SalableBook(book, book.getAmount()));
			// } else {
			// boolean foundActive = false;
			// for (SalableBook salableBook : books) {
			// if (salableBook.isActive()) {
			// salableBook.updateAmount(book.getAmount());
			// foundActive = true;
			// }
			// }
			// if (!foundActive) {
			//
			// }
			// }
		}

	}

	public SalableBook createSalableBook(Book book, Long amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
