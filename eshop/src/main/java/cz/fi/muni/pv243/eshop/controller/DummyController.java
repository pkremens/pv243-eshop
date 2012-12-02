package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.fi.muni.pv243.eshop.model.Dummy;

@Named("dummyController")
@SessionScoped
public class DummyController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8198535137115961421L;
	private final List<Dummy> dummies = new ArrayList<Dummy>();

	public List<Dummy> getDummies() {
		return dummies;
	}

	@Inject
	private Logger log;

	public void update() {
		log.info("update action");
		for (Dummy dummy : dummies) {
			log.info(dummy.toString());
		}
	}

	@PostConstruct
	public void retrieveAllBooks() {
		dummies.add(new Dummy("foo1", "bar1"));
		dummies.add(new Dummy("foo1", "bar1"));
	}
}
