package com.mechanicshop.components;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class MaintenanceLayout extends HorizontalLayout {
	
	SimpleJDBCConnectionPool connectionPool;
	Table table = new Table();
	
	@PostConstruct
	void init() {
		addStyleName(ValoTheme.LAYOUT_WELL);
		setMargin(true);
		setSizeFull();
		buildLayout();
		
		try {
			connectionPool = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/t4l", "root",
					"");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void buildLayout() {
		VerticalLayout layoutParameters = new VerticalLayout();
		VerticalLayout layoutTable = new VerticalLayout();
			
			
			
			
			addComponent(layoutParameters);
			addComponent(layoutTable);
		}
	

}
