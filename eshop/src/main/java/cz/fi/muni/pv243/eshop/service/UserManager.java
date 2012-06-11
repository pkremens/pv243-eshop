package cz.fi.muni.pv243.eshop.service;

import java.util.List;

import cz.fi.muni.pv243.eshop.model.User;

public interface UserManager {

	public List<User> getUsers() throws Exception;

	public String addUser() throws Exception;

	public User findUser(String username, String password) throws Exception;

	public User getNewUser();

}