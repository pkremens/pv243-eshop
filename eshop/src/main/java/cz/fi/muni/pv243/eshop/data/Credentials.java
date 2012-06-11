package cz.fi.muni.pv243.eshop.data;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@RequestScoped
@Named
public class Credentials {

	@NotNull
	@NotEmpty
	@Email
	// TODO pridat hlasku pokud se pokusi prihlasit pod nesmyslnym nebo
	// neexistujicim emailem
	private String email;

	@NotNull
	private String password;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}