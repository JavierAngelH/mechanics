package com.mechanicshop.service;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

@Component
public class ConnectionPool extends SimpleJDBCConnectionPool {

	
	public ConnectionPool() throws SQLException {
		super("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/t4l?autoReconnect=true", "root",
				"1234",5,40);
		
	}
	


}
