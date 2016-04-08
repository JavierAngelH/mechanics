package com.mechanicshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.mechanicshop.components.DataEntryLayout;
import com.mechanicshop.components.MaintenanceLayout;
import com.mechanicshop.components.SmsLayout;
import com.mechanicshop.components.TableLayout;
import com.mechanicshop.service.SearchService;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.steinwedel.messagebox.MessageBox;



@SpringComponent
@UIScope
public class MechanicShopView extends VerticalLayout implements View {

	MenuBar barmenu = new MenuBar();
	SQLContainer container = null;
	VerticalLayout layoutContent = new VerticalLayout();
	
	@Autowired
	SmsLayout smsLayout;
	
	@Autowired
	TableLayout tableLayout;
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	DataEntryLayout dataEntryLayout;
	
	@Autowired
	MaintenanceLayout maintenanceLayout;
	
	TextField input = new TextField();

	@PostConstruct
    void init() {
		setSizeFull();
		buildMenu();
		tableLayout.fillTable("Cars In");
		addComponents(barmenu, tableLayout);

		setExpandRatio(tableLayout, 3);
    }


	private void buildMenu() {
		barmenu.setImmediate(true);
		;
		barmenu.setWidth("100%");
		barmenu.addStyleName(ValoTheme.MENUBAR_SMALL);
		barmenu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		// Define a common menu command for all the menu items
		MenuBar.Command mycommand = new MenuBar.Command() {
			// MenuItem previous = null;

			public void menuSelected(MenuItem selectedItem) {
				String selectedOption = selectedItem.getText();
				switch (selectedOption) {
				
				case "Data Entry":
					removeComponent(tableLayout);
					removeComponent(smsLayout);
					removeComponent(maintenanceLayout);
					addComponent(dataEntryLayout);	
					setExpandRatio(dataEntryLayout, 3);

					break;
				
				case "SMS":
					removeComponent(tableLayout);
					removeComponent(dataEntryLayout);
					removeComponent(maintenanceLayout);
					addComponent(smsLayout);
					setExpandRatio(smsLayout, 3);
					smsLayout.fillInbox();
					break;
				case "Maintenance":
					MessageBox.createQuestion().withCaption("Type Password").withIcon(null)
					.withMessage(input)
					.withOkButton(new Runnable() {

				@Override
				public void run() {
					if(searchService.validatePassword(input.getValue())){
					removeComponent(tableLayout);
					removeComponent(dataEntryLayout);
					removeComponent(smsLayout);
					addComponent(maintenanceLayout);
					setExpandRatio(maintenanceLayout, 3);}
					else
						Notification.show("Wrong Password", Notification.Type.ERROR_MESSAGE);
				}
			}).open();
					
				
					break;
				default:
					tableLayout.fillTable(selectedOption);
					removeComponent(smsLayout);
					removeComponent(dataEntryLayout);
					removeComponent(maintenanceLayout);
					addComponent(tableLayout);
					setExpandRatio(tableLayout, 3);
					break;
				
				}
				
			}
		};

		// Put some items in the menu
		barmenu.addItem("Cars In", FontAwesome.ARROW_RIGHT, mycommand);
		barmenu.addItem("Cars Comeback", FontAwesome.ARROW_CIRCLE_RIGHT, mycommand);
		barmenu.addItem("Cars Pending", FontAwesome.CLOCK_O, mycommand);
		barmenu.addItem("Cars Out", FontAwesome.ARROW_LEFT, mycommand);
		barmenu.addItem("Cars Ready", FontAwesome.CAR, mycommand);
		barmenu.addItem("SMS", FontAwesome.ENVELOPE, mycommand);
		barmenu.addItem("Data Entry", FontAwesome.PENCIL, mycommand);
		barmenu.addItem("Maintenance", FontAwesome.BOOK,mycommand);
	}


	@Override
	public void enter(ViewChangeEvent event) {
		
	}


	
	

	
}
