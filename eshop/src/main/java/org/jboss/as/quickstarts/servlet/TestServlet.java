package org.jboss.as.quickstarts.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.fi.muni.pv243.eshop.model.Book;
import cz.fi.muni.pv243.eshop.service.BookEJB;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int isbnHelper = 1;

	@Inject
	Book book;

	@Inject
	BookEJB bookController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		book.setAmount(6L);
		book.setDescription("blabla");
		book.setEditable(true);
		book.setIllustrations(false);
		book.setInBasket(false);
		book.setIsbn("11-22".concat(String.valueOf(isbnHelper++)));
		book.setNbOfPage(198);
		book.setTitle("Around the world and back");

		bookController.createBook(book);
		out.write(book.toString());

		out.write("<br />Hello<br /><br />");
		out.write("<a href=\"/eshop/listBooks.jsf\">");
		out.write("list books</a>");
		out.close();
		book = new Book();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
