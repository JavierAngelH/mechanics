package com.mechanicshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import com.vaadin.data.Item;



@Repository
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

	@Override
	public boolean validatePassword(String password) {
		String sql = "SELECT AdminPassword FROM parameters ";
		Connection conn = null;
		String encodedPassword = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
					encodedPassword = rs.getString("AdminPassword");
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
		String decodedPassword = Base64.base64Decode(encodedPassword);
		if(decodedPassword.equals(password))
			return true;
		return false;
	}

	@Override
	public void updatePassword(String password) {
		byte[] encodedBytes = Base64.encode(password.getBytes());
		String encodedPassword = new String(encodedBytes);
		String sql = "UPDATE parameters SET AdminPassword = ?";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, encodedPassword);
			ps.executeUpdate();
		
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
		
	}

	@Override
	public String getMedia1() {
		String sql = "SELECT DefaultMedia1 FROM parameters ";
		Connection conn = null;
		String media = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				media = rs.getString("DefaultMedia1");
							
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
		
		return media;
	}

	@Override
	public String getMedia2() {
		String sql = "SELECT DefaultMedia2 FROM parameters ";
		Connection conn = null;
		String media = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				media = rs.getString("DefaultMedia2");
							
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
		
		return media;
	}

	@Override
	public void updateMedia1(String text) {
		String sql = "UPDATE parameters SET DefaultMedia1 = ?";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, text);
			ps.executeUpdate();
		
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
		
	}

	@Override
	public void updateMedia2(String text) {
		String sql = "UPDATE parameters SET DefaultMedia2 = ?";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, text);
			ps.executeUpdate();
		
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
	}

	@Override
	public void insertCar(Object[] args, String status) {
		String tableName = "cars"+"_"+status.toLowerCase();
		String sql = "INSERT INTO "+ tableName+ "(Tag,Phone,Name,"
				+ "Vehicle,LicensePlate,Vin,InShop,OutShop,Status,Mileage,Picked,Payment,"
				+ "Remarks,Rebuilder,Installer,FirstCheckBy,SecondCheckBy,FirstCheckDate,"
				+ "SecondCheckDate,Media,Media2,ReferedBy,WarrantyLimit,Warranty,SMS,Comeback)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			 for(int i = 0; i < args.length; i++) {
				 Object object = args[i];
				if(object instanceof String){
					String stringRepresentation = (String) object;
					if(stringRepresentation.isEmpty())
						object = null;
				}

		        	ps.setObject(i+1, object);
		        }
			ps.executeUpdate();
		
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
	}

	@Override
	public void editCar(Object[] args, String tableName, Integer no) {
		
		String sql = "UPDATE "+ tableName+ " SET Tag=?,Phone=?,Name=?,"
				+ "Vehicle=?,LicensePlate=?,Vin=?,InShop=?,OutShop=?,Status=?,Mileage=?,Picked=?,Payment=?,"
				+ "Remarks=?,Rebuilder=?,Installer=?,FirstCheckBy=?,SecondCheckBy=?,FirstCheckDate=?,"
				+ "SecondCheckDate=?,Media=?,Media2=?,ReferedBy=?,WarrantyLimit=?,Warranty=?,SMS=?,Comeback=? WHERE No = ?"
				;
		
		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(args.length + 1, no);
			 for(int i = 0; i < args.length; i++) {
				 Object object = args[i];
				if(object instanceof String){
					String stringRepresentation = (String) object;
					if(stringRepresentation.isEmpty())
						object = null;
				}

		        	ps.setObject(i+1, object);
		        }
			ps.executeUpdate();
			String status = (String) args[8];
			if(!tableName.contains(status.toLowerCase())){
				String sqlDelete = "DELETE FROM " + tableName + " WHERE No = ?";
				ps = conn.prepareStatement(sqlDelete);
				ps.setInt(1, no);
				ps.executeUpdate();
			}
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
	}

	@Override
	public void insertIntoUnknownTable(Item item) {
		String sql = "INSERT INTO  cars_unknown (Tag,Phone,Name,"
				+ "Vehicle,LicensePlate,Vin,InShop,OutShop,Status,Mileage,Picked,Payment,"
				+ "Remarks,Rebuilder,Installer,FirstCheckBy,SecondCheckBy,FirstCheckDate,"
				+ "SecondCheckDate,Media,Media2,ReferedBy,WarrantyLimit,Warranty,SMS,Comeback,"
				+ " DateDeleted)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, item.getItemProperty("Tag").getValue());
			ps.setObject(2, item.getItemProperty("Phone").getValue());
			ps.setObject(3, item.getItemProperty("Name").getValue());
			ps.setObject(4, item.getItemProperty("Vehicle").getValue());
			ps.setObject(5, item.getItemProperty("LicensePlate").getValue());
			ps.setObject(6, item.getItemProperty("Vin").getValue());
			ps.setObject(7, item.getItemProperty("InShop").getValue());
			ps.setObject(8, item.getItemProperty("OutShop").getValue());
			ps.setObject(9, item.getItemProperty("Status").getValue());
			ps.setObject(10, item.getItemProperty("Mileage").getValue());
			ps.setObject(11, item.getItemProperty("Picked").getValue());
			ps.setObject(12, item.getItemProperty("Payment").getValue());
			ps.setObject(13, item.getItemProperty("Remarks").getValue());
			ps.setObject(14, item.getItemProperty("Rebuilder").getValue());
			ps.setObject(15, item.getItemProperty("Installer").getValue());
			ps.setObject(16, item.getItemProperty("FirstCheckBy").getValue());
			ps.setObject(17, item.getItemProperty("SecondCheckBy").getValue());
			ps.setObject(18, item.getItemProperty("FirstCheckDate").getValue());
			ps.setObject(19, item.getItemProperty("SecondCheckDate").getValue());
			ps.setObject(20, item.getItemProperty("Media").getValue());
			ps.setObject(21, item.getItemProperty("Media2").getValue());
			ps.setObject(22, item.getItemProperty("ReferedBy").getValue());
			ps.setObject(23, item.getItemProperty("WarrantyLimit").getValue());
			ps.setObject(24, item.getItemProperty("Warranty").getValue());
			ps.setObject(25, item.getItemProperty("SMS").getValue());
			ps.setObject(26, item.getItemProperty("Comeback").getValue());
			ps.setObject(27, new Date());
			ps.executeUpdate();
		
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
	}

}
