package com.mechanicshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechanicshop.utils.Utilities;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.instance.Sms;
import com.twilio.sdk.resource.list.SmsList;

@Service
public class SmsSenderServiceImpl implements SmsSenderService {

	@Autowired
	private DataSource dataSource;

	public void sendMessage(String message, String number) throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(Utilities.TWILIO_ACCOUNT_SID, Utilities.TWILIO_AUTH_TOKEN);

		Account account = client.getAccount();

		MessageFactory messageFactory = account.getMessageFactory();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("To", number));
		params.add(new BasicNameValuePair("From", Utilities.TWILIO_PHONE_NUMBER));
		params.add(new BasicNameValuePair("Body", message));
		Message sms = messageFactory.create(params);
		System.out.println(sms.getStatus());
	}

	public void sendMessagesMassive() {
		String sql = "SELECT no, phone, media FROM cars_ready where SMS =  'NO'";
		String sqlUpdate = "Update cars_ready set SMS = 'YES' where no = ?";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int id = 0;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				try {

					sendMessage(rs.getString("Media"), rs.getString("Phone"));
					id = rs.getInt("No");
					PreparedStatement psu = conn.prepareStatement(sqlUpdate);
					psu.setInt(1, id);
					psu.executeUpdate();
					psu.close();
					sendVoiceMessage(rs.getString("Media"), rs.getString("Phone"));
				} catch (TwilioRestException e) {
					e.printStackTrace();
				}
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public void sendVoiceMessage(String message, String number) throws TwilioRestException {
		// Replace spaces in the message text with '%20',
		// to make the text suitable for a URL.
		message = message.replace(" ", "%20");

		// Create a URL using the Twilio message and the user-entered text.
		String Url = "http://twimlets.com/message";
		Url = Url + "?Message%5B0%5D=" + message;
		TwilioRestClient client = new TwilioRestClient(Utilities.TWILIO_ACCOUNT_SID, Utilities.TWILIO_AUTH_TOKEN);

		Account mainAccount = client.getAccount();
		CallFactory callFactory = mainAccount.getCallFactory();
		Map<String, String> callParams = new HashMap<String, String>();
		callParams.put("To", number);
		callParams.put("From", Utilities.TWILIO_PHONE_NUMBER);
		callParams.put("Url", Url);

		Call call = callFactory.create(callParams);

		System.out.println(call.getSid());
	}

	@Override
	public List<Sms> getReceivedMessages() {
		List<Sms> list = new ArrayList<Sms>();
		TwilioRestClient client = new TwilioRestClient(Utilities.TWILIO_ACCOUNT_SID, Utilities.TWILIO_AUTH_TOKEN);

		Account account = client.getAccount();

		SmsList smsList = account.getSmsMessages();

		for (Sms sms : smsList) {
			if(sms.getDirection().equalsIgnoreCase("inbound"))
			list.add(sms);
			
		}
		return list;
	}

	@Override
	public void sendMessageMassive(String message) {
		String sql = "SELECT no, phone FROM cars_ready where SMS =  'NO'";
		String sqlUpdate = "Update cars_ready set SMS = 'YES' where no = ?";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int id = 0;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				try {

					sendMessage(message, rs.getString("Phone"));
					id = rs.getInt("No");
					PreparedStatement psu = conn.prepareStatement(sqlUpdate);
					psu.setInt(1, id);
					psu.executeUpdate();
					psu.close();
					sendVoiceMessage(message, rs.getString("Phone"));
				} catch (TwilioRestException e) {
					e.printStackTrace();
				}
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public void sendReminderMessagesMassive() {
		String sql = "select no, phone, media2 from all_tables where DATEDIFF(sysdate(),outshop) = 7 OR DATEDIFF(sysdate(),outshop) = 60";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				try {

					sendMessage(rs.getString("Media"), rs.getString("Phone"));
					sendVoiceMessage(rs.getString("Media"), rs.getString("Phone"));
				} catch (TwilioRestException e) {
					e.printStackTrace();
				}
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

}