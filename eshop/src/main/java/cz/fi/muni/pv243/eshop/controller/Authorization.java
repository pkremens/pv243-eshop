package cz.fi.muni.pv243.eshop.controller;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.service.Admin;
import cz.fi.muni.pv243.eshop.service.BasicPermission;
import cz.fi.muni.pv243.eshop.service.Seller;

public class Authorization {

	@Inject
	Logger log;

	@Secures
	@Admin
	public boolean isAdmin(Identity identity) {
		if (!identity.isLoggedIn()) {
			return false;
		}
		log.finest("Authorization: user is authorized as admin");
		return "admin".equals(((Customer) identity.getUser()).getRole());
	}

	@Secures
	@Seller
	public boolean isSeller(Identity identity) {
		if (!identity.isLoggedIn()) {
			return false;
		}
		log.finest("Authorization: user is authorized as seller");
		return "seller".equals(((Customer) identity.getUser()).getRole());
	}

	@Secures
	@BasicPermission
	public boolean isCustomer(Identity identity) {
		if (!identity.isLoggedIn()) {
			return false;
		}
		log.finest("Authorization: user has default permission level");
		return "customer".equals(((Customer) identity.getUser()).getRole());
	}
}
