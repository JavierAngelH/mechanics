package com.mechanicshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.sdk.TwilioRestException;



@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private DataSource dataSource;

	public String findFilter(String text) {
		String table = null;
		String sql = "SELECT No, Tag, Phone, Name, Vehicle, LicensePlate, Vin, InShop, OutShop, Status, "
				+ "Mileage, Picked, Payment, Remarks, Rebuilder, Installer, FirstCheckBy, SecondCheckBy, "
				+ "FirstCheckDate, SecondCheckDate, Media, ReferedBy, WarrantyLimit, Warranty FROM all_tables "
				+ " WHERE (UPPER(Phone) LIKE " + "'%" +text+"%'" +" OR UPPER(Name) LIKE " + "'%" +text+"%'" +" OR UPPER(Vehicle) LIKE " + "'%" +text+"%'" +" OR UPPER(LicensePlate) "
				+ "LIKE " + "'%" +text+"%'" +" OR UPPER(Vin) LIKE " + "'%" +text+"%'" +" OR UPPER(LicensePlate) LIKE " + "'%" +text+"%'" +" OR UPPER(Status) "
				+ "LIKE " + "'%" +text+"%'" +" OR UPPER(Picked) LIKE " + "'%" +text+"%'" +" OR UPPER(Payment) LIKE " + "'%" +text+"%'" +" OR UPPER(Remarks) LIKE " + "'%" +text+"%'" +" OR UPPER(Rebuilder) "
				+ "LIKE " + "'%" +text+"%'" +" OR UPPER(Installer) LIKE " + "'%" +text+"%'" +" OR UPPER(FirstCheckBy) LIKE " + "'%" +text+"%'" +" OR UPPER(SecondCheckBy) LIKE " + "'%" +text+"%'" +" "
				+ "OR UPPER(Media) LIKE " + "'%" +text+"%'" +" OR UPPER(ReferedBy) LIKE " + "'%" +text+"%'" +" OR UPPER(WarrantyLimit) LIKE " + "'%" +text+"%'" +" OR "
				+ "UPPER(Warranty) LIKE " + "'%" +text+"%'" +" OR UPPER(No) LIKE " + "'%" +text+"%'" +" OR UPPER(Tag) LIKE " + "'%" +text+"%'" +" OR UPPER(Mileage) LIKE "
				+ "" + "'%" +text+"%'" +")";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			 table = rs.getString("Status");
		

			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		 finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		
			
		return table;
		}

	@Override
	public List<String> getClients() {
		List<String> resultList = new ArrayList<String>();
		String sql = "SELECT CONCAT(name, ' -- ', phone) as Client FROM all_tables ";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String client = rs.getString("Client");
				if(client!=null)
					resultList.add(client);
			
			
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		 finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		
			
		return resultList;
	}

}
