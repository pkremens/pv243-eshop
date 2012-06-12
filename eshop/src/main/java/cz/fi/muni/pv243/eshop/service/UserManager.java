package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import org.picketlink.idm.impl.api.PasswordCredential;

import cz.fi.muni.pv243.eshop.model.User;

public interface UserManager {

	public List<User> getUsers() throws Exception;

	public String addUser() throws Exception;

	public User findUser(String username, PasswordCredential passwordCredential) throws Exception;

	public User getNewUser();

	User findUser(String email, String passwordCredential) throws Exception; //TODO erase

}