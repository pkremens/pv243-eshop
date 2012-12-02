/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import cz.fi.muni.pv243.eshop.model.Dummy;

/**
 * <p>
 * A simple Message Driven Bean that asynchronously receives and processes the
 * messages that are sent to the queue.
 * </p>
 * 
 * @author Serge Pagop (spagop@redhat.com)
 * 
 */
@MessageDriven(name = "DummyMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		// @ActivationConfigProperty(propertyName = "destination", propertyValue
		// = "queue/HELLOWORLDMDBQueue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class DummyMDB implements MessageListener {

	@Inject
	private Logger LOGGER;

	@Inject
	private Dummy dummy;

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	@Override
	public void onMessage(Message rcvMessage) {
		try {
			if (rcvMessage instanceof MapMessage) {
				MapMessage msg = (MapMessage) rcvMessage;
				LOGGER.info("Received Message");
				dummy.setBar(msg.getStringProperty("bar"));
				dummy.setFoo(msg.getStringProperty("foo"));
				LOGGER.info(dummy.toString());
			} else {
				LOGGER.warning("Message of wrong type: "
						+ rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
