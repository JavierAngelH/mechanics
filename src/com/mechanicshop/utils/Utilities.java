package com.mechanicshop.utils;

import java.io.File;

import com.vaadin.server.VaadinService;

public class Utilities {


	 public static final String TWILIO_ACCOUNT_SID = "AC0a41ec77ec130f1b87e4cdee7a8a7f34";
	 public static final String TWILIO_AUTH_TOKEN = "4b8f82284555348da621b3886f794100";
	 public static final String TWILIO_PHONE_NUMBER = "+1 773-985-8885";

	 public static final String ABSOLUTE_PATH = VaadinService.getCurrent()
			.getBaseDirectory().getAbsolutePath();
 
	 public static final String CALL_XML_PATH = ABSOLUTE_PATH + File.separator + "call.xml";
}
