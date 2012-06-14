package cz.fi.muni.pv243.eshop.controller;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SelectOneMenuBean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String submit() {
		return "selectOneMenu";
	}
}
