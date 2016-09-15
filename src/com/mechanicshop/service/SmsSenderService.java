package com.mechanicshop.service;

import java.util.List;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Sms;

public interface SmsSenderService {
	void sendMessage(String message, String number) throws TwilioRestException;
	
	void sendVoiceMessage(String message, String number) throws TwilioRestException;
	
	void sendMessagesMassive();
	
	void sendMessageMassive(String message);
	
	List<Sms> getReceivedMessages();
	
	void sendReminderMessagesMassive();
}
