package com.mechanicshop;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;



/**
 * 
 * @author Javier Angel
 * This is the first class loaded in the application. These annotations are required
 * to integrate vaadin with spring
 */
@SuppressWarnings("serial")
@Theme("valo")
@SpringUI
public class MechanicShopUI extends UI {

	
	@Autowired
	MechanicShopView mechanicShopView;
	
	 @WebServlet(value = "/*", asyncSupported = true)
	    public static class Servlet extends SpringVaadinServlet {
	    }

	    @WebListener
	    public static class MyContextLoaderListener extends ContextLoaderListener {
	    }

	    @Configuration
	    @EnableVaadin
	    public static class MyConfiguration {
	    }

	@Override
	protected void init(VaadinRequest request) {
		
	
		Page.getCurrent().setTitle("Mechanic Shop");
		
		setContent(mechanicShopView);
		
		

	}

	

}