package cz.fi.muni.pv243.eshop.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.fi.muni.pv243.eshop.model.User;

@Named("userManager")
@RequestScoped
@Stateful
public class EJBUserManager implements UserManager {

	@Inject
	private transient Logger logger;

	@Inject
	private EntityManager userDatabase;

	private final User newUser = new User();

	@Override
	@SuppressWarnings("unchecked")
	@Produces
	@Named
	@RequestScoped
	public List<User> getUsers() throws Exception {
		return userDatabase.createQuery("select u from User u").getResultList();
	}

	@Override
	public String addUser() throws Exception {
		userDatabase.persist(newUser);
		logger.info("Added " + newUser);
		return "userAdded";
	}

	@Override
	public User findUser(String email, String password) throws Exception {
		@SuppressWarnings("unchecked")
		List<User> results = userDatabase
				.createQuery(
						"select u from User u where u.email=:email and u.password=:password")
				.setParameter("email", email)
				.setParameter("password", password).getResultList();
		if (results.isEmpty()) {
			return null;
		} else if (results.size() > 1) {
			throw new IllegalStateException(
					"Cannot have more than one user with the same email!");
		} else {
			return results.get(0);
		}
	}

	@Override
	@Produces
	@RequestScoped
	@Named
	public User getNewUser() {
		return newUser;
	}

}
