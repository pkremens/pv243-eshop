package cz.fi.muni.pv243.eshop.controller;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.model.User;

public class Authenticator extends BaseAuthenticator
{
   @Inject
   Credentials credentials;

   @Inject
   EntityManager em;
   
   
   @Override
   public void authenticate()
   {
	   
	/*   User user = null;
	   
	try {
		user = userManager.findUser(credentials.getUsername(),
		   ((PasswordCredential)credentials.getCredential()).getValue());
	} catch (Exception e) {
		//TODO logger
	}
	   
		if (user != null) {
			this.currentUser = user;
			setStatus(AuthenticationStatus.SUCCESS);
	        setUser((org.picketlink.idm.api.User) user);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Welcome, " + currentUser.getName()));
		} else {
			setStatus(AuthenticationStatus.FAILURE);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"Non existing user, or passoword or both :)"));
		}*/

	   
      try {
         Customer customer = (Customer)em.createQuery("select m from Customer m where m.email = :email and m.password = :password")
         .setParameter("email", credentials.getUsername())
         .setParameter("password", ((PasswordCredential)credentials.getCredential()).getValue()).getSingleResult();
         
         setStatus(AuthenticationStatus.SUCCESS);
         setUser(customer);
         System.err.println(customer.toString());
         FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Welcome, " + customer.getName()));
      }
      catch(NoResultException x) {
         setStatus(AuthenticationStatus.FAILURE);
         FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"Non existing user, or passoword or both :)"));
      }
   }
   
//   public void logout() {
//	   setUser(null);
//	   System.err.println("this is called");
//		FacesContext.getCurrentInstance().addMessage(null,
//				new FacesMessage("Goodbye, " + ((Customer)getUser()).getName()));
//		
//	}
//
//	public boolean isLoggedIn() {
//		return getUser() != null;
//	}
//
//	@Produces
//	@LoggedIn
//	public Customer getCurrentUser() {
//		return (Customer)getUser();
//	}

}
