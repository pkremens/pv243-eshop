package cz.fi.muni.pv243.eshop.controller;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import org.jboss.logging.Logger;

@Model
public class LoggingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3233569998061759697L;

	public void log() {
		Logger log = Logger.getLogger(this.getClass().toString());
		log.info("ahoj");
		System.out.println("TEST");
	}

}