package com.mechanicshop.utils;

import java.io.File;
import java.text.SimpleDateFormat;

import com.vaadin.server.VaadinService;

public class Utilities {


	 public static final String TWILIO_ACCOUNT_SID = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	 public static final String TWILIO_AUTH_TOKEN = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	 public static final String TWILIO_PHONE_NUMBER = "+1 XXX-XXX-XXX";

	 public static final String ABSOLUTE_PATH = VaadinService.getCurrent()
			.getBaseDirectory().getAbsolutePath();
	 
	 public static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd hh:mm a ");
 
}
