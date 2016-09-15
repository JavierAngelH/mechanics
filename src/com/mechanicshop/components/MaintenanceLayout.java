package com.mechanicshop.components;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import tm.kod.widgets.numberfield.NumberField;

import com.mechanicshop.service.ConnectionPool;
import com.mechanicshop.service.SearchService;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class MaintenanceLayout extends VerticalLayout {
	
	@Autowired
	ConnectionPool connectionPool;
	
	Table table = new Table();
	Label titleLabel = new Label("MAINTENANCE");
	SQLContainer container = null;
	

	@Autowired
	DataEntryLayout dataEntryLayout;
	
	@Autowired
	SearchService searchService;
	
	@PostConstruct
	void init() {
		addStyleName(ValoTheme.LAYOUT_WELL);
		setMargin(true);
		setSizeFull();
		buildLayout();
		customizeTable();
		fillTable();

	}

	public void fillTable() {
		
		try {
				container = new SQLContainer(new TableQuery("cars_unknown", connectionPool));
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table.setEditable(false);
		table.setContainerDataSource(container);
		table.setColumnHeaders(new String[] { "No", "Tag", "Phone", "Name", "Vehicle", "License Plate", "Vin",
				"In Shop", "Out Shop", "Status", "Mileage", "Picked", "Payment", "Remarks", "Rebuilder", "Installer",
				"First Check By", "Second Check By", "First Check Date", "Second Check Date", "Media" , "Media 2", "Refered By",
				"Warranty Limit", "Warranty", "SMS Sent", "Comeback", "Deletion Date", " " });

		table.setColumnWidth("", 35);
		table.setColumnWidth("No", 60);
		table.setColumnWidth("Tag", 90);
		table.setColumnWidth("Phone", 150);
		table.setColumnWidth("Name", 170);
		table.setColumnWidth("Vehicle", 150);
		table.setColumnWidth("LicensePlate", 150);
		table.setColumnWidth("Vin", 145);
		table.setColumnWidth("InShop", 160);
		table.setColumnWidth("OutShop", 160);
		table.setColumnWidth("Status", 120);
		table.setColumnWidth("Mileage", 90);
		table.setColumnWidth("Picked", 60);
		table.setColumnWidth("Rebuilder", 150);
		table.setColumnWidth("Installer", 150);
		table.setColumnWidth("ReferedBy", 150);

		table.setColumnWidth("Media", 300);

		table.setColumnWidth("Media2", 300);
		table.setColumnWidth("WarrantyLimit", 120);
		table.setColumnWidth("Warranty", 90);
		table.setColumnWidth("FirstCheckDate", 160);
		table.setColumnWidth("SecondCheckDate", 160);
		table.setColumnWidth("SMS", 85);
		table.setColumnWidth("Comeback", 85);
		table.setColumnWidth("DateDeleted", 180);
		table.setVisibleColumns(" ",  "No", "DateDeleted", "Tag", "Phone", "Name", "Vehicle", "LicensePlate", "Vin", "InShop",
					"OutShop", "Status", "Mileage", "Picked", "Payment", "Remarks", "Rebuilder", "Installer",
					"FirstCheckBy", "SecondCheckBy", "FirstCheckDate", "SecondCheckDate", "Media", "Media2", "ReferedBy",
					"WarrantyLimit", "Warranty", "Comeback", "SMS");
		table.setCaption("Deleted Items");

	}

	

	
	private void buildLayout() {
		HorizontalLayout layoutTitle = new HorizontalLayout();
		layoutTitle.setSizeUndefined();
		layoutTitle.setWidth("100%");
		layoutTitle.setSpacing(false);
		layoutTitle.setMargin(false);
		titleLabel.addStyleName(ValoTheme.LABEL_H2);
		titleLabel.addStyleName(ValoTheme.LABEL_COLORED);
		titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		titleLabel.addStyleName(ValoTheme.LABEL_BOLD);
		titleLabel.setSizeUndefined();

		layoutTitle.addComponent(titleLabel);
		layoutTitle.setComponentAlignment(titleLabel, Alignment.MIDDLE_CENTER);

		VerticalLayout layoutTable = new VerticalLayout();

		

		layoutTable.setSizeFull();

		layoutTable.setSpacing(true);
		HorizontalLayout layoutButtons = new HorizontalLayout();
		layoutButtons.setMargin(false);
		layoutButtons.setSpacing(true);
		layoutButtons.setSizeUndefined();
			Button passwordBtn = new Button("Edit Password");
		passwordBtn.addClickListener(passwordListener);
		passwordBtn.setImmediate(true);
		passwordBtn.setIcon(FontAwesome.EDIT);
		passwordBtn.setStyleName(ValoTheme.BUTTON_TINY);
		Button media1Btn = new Button("Media 1 Default Text");
		media1Btn.setStyleName(ValoTheme.BUTTON_TINY);
		media1Btn.setImmediate(true);
		media1Btn.setIcon(FontAwesome.EDIT);
		media1Btn.addClickListener(media1Listener);
		Button media2Btn = new Button("Media 2 Default Text");
		media2Btn.setStyleName(ValoTheme.BUTTON_TINY);
		media2Btn.setImmediate(true);
		media2Btn.setIcon(FontAwesome.EDIT);
		media2Btn.addClickListener(media2Listener);
		layoutButtons.addComponents(passwordBtn, media1Btn, media2Btn);

		
		layoutButtons.setComponentAlignment(passwordBtn, Alignment.MIDDLE_LEFT);
		layoutButtons.setComponentAlignment(media1Btn, Alignment.MIDDLE_LEFT);
		layoutButtons.setComponentAlignment(media2Btn, Alignment.MIDDLE_LEFT);
		
		addComponent(layoutTitle);
		addComponent(layoutTable);
		layoutTable.addComponent(layoutButtons);
		layoutTable.addComponent(table);
		layoutTable.setComponentAlignment(table, Alignment.TOP_CENTER);
		layoutTable.setExpandRatio(table, 3);
		setComponentAlignment(layoutTitle, Alignment.TOP_CENTER);
		setComponentAlignment(layoutTable, Alignment.TOP_CENTER);
		setExpandRatio(layoutTable, 3);
		setSpacing(true);
		setMargin(true);

	}

	private void customizeTable() {
		table.setSizeFull();
		table.setSortEnabled(true);
		table.setStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
		table.addStyleName(ValoTheme.TABLE_SMALL);
		table.setEditable(true);
		table.setImmediate(true);
		
		table.addGeneratedColumn(" ", new Table.ColumnGenerator() {

			@Override
			public Object generateCell(final Table source, final Object itemId, Object columnId) {
				Button icon = new Button();
				icon.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
				icon.addStyleName(ValoTheme.BUTTON_TINY);
				icon.addStyleName(ValoTheme.BUTTON_BORDERLESS);
				icon.setVisible(true);
				icon.setImmediate(true);
				icon.setDescription("Details");
				icon.setIcon(FontAwesome.PENCIL);
				icon.addClickListener(new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						Item item = source.getItem(itemId);
						showDataEntryWindow(item);
					}
				});
				return icon;
			}
		});

	}
	
	
	void showDataEntryWindow(Item item){
		dataEntryLayout.fillDataEntry(item, "cars_unknown");
		getUI().addWindow(dataEntryLayout);
	}
	

	ClickListener passwordListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			
			final PasswordField textField = new PasswordField();
			textField.setImmediate(true);
			textField.addStyleName(ValoTheme.TEXTFIELD_SMALL);
			textField.setRequired(true);
			final Window subWindow = new Window();
			subWindow.setModal(true);
			subWindow.setHeight("150px");
			subWindow.setWidth("250px");
			subWindow.setCaption("Insert New Password");
			subWindow.setStyleName(ValoTheme.WINDOW_TOP_TOOLBAR);
			subWindow.setClosable(false);
			subWindow.setResizable(false);
			
			HorizontalLayout layoutButtons = new HorizontalLayout();
			layoutButtons.setMargin(false);
			Button sendBtn = new Button("Save");
			sendBtn.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					try{
						textField.validate();
					String password = textField.getValue();
					searchService.updatePassword(password);
					subWindow.close();
					Notification.show("Password Updated");
					}catch(Exception e){
						
						e.printStackTrace();
					}
				}
			});
			sendBtn.setImmediate(true);
			sendBtn.setStyleName(ValoTheme.BUTTON_TINY);
			sendBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
			Button cancelBtn = new Button("Cancel");
			cancelBtn.setStyleName(ValoTheme.BUTTON_TINY);
			cancelBtn.addStyleName(ValoTheme.BUTTON_DANGER);
			cancelBtn.setImmediate(true);
			cancelBtn.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					subWindow.close();
					
				}
			});
			
			layoutButtons.setSizeUndefined();
			layoutButtons.setSpacing(true);
			layoutButtons.addComponents(cancelBtn, sendBtn);
			
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);
			layout.setMargin(true);
			layout.addComponent(textField);
			layout.addComponent(layoutButtons);
			layout.setComponentAlignment(textField, Alignment.MIDDLE_CENTER);
			layout.setComponentAlignment(layoutButtons, Alignment.MIDDLE_RIGHT);
			layout.setExpandRatio(textField, 3);
			
			layout.setSizeFull();
			
			
			subWindow.setContent(layout);
			subWindow.center();

			getUI().addWindow(subWindow);
		}
	};
	
	
ClickListener media1Listener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			String currentMedia = searchService.getMedia1();
			final TextArea textArea = new TextArea();
			textArea.setImmediate(true);
			textArea.addStyleName(ValoTheme.TEXTFIELD_SMALL);
			textArea.setValue(currentMedia);
			textArea.setNullRepresentation("");
			textArea.setInputPrompt("Insert Text Here");
			textArea.setColumns(30);
			textArea.setRows(10);
			final Window subWindow = new Window();
			subWindow.setModal(true);
			subWindow.setHeight("350px");
			subWindow.setWidth("500px");
			subWindow.setCaption("Default Text Media 1");
			subWindow.setStyleName(ValoTheme.WINDOW_TOP_TOOLBAR);
			subWindow.setClosable(false);
			subWindow.setResizable(false);
			
			HorizontalLayout layoutButtons = new HorizontalLayout();
			layoutButtons.setMargin(false);
			Button sendBtn = new Button("Save");
			sendBtn.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					try{
				
					String text = textArea.getValue();
					searchService.updateMedia1(text);
					subWindow.close();
					Notification.show("Default Text Updated");
					}catch(Exception e){
						
						e.printStackTrace();
					}
				}
			});
			sendBtn.setImmediate(true);
			sendBtn.setStyleName(ValoTheme.BUTTON_TINY);
			sendBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
			Button cancelBtn = new Button("Cancel");
			cancelBtn.setStyleName(ValoTheme.BUTTON_TINY);
			cancelBtn.addStyleName(ValoTheme.BUTTON_DANGER);
			cancelBtn.setImmediate(true);
			cancelBtn.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					subWindow.close();
					
				}
			});
			
			layoutButtons.setSizeUndefined();
			layoutButtons.setSpacing(true);
			layoutButtons.addComponents(cancelBtn, sendBtn);
			
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);
			layout.setMargin(true);
			layout.addComponent(textArea);
			layout.addComponent(layoutButtons);
			layout.setComponentAlignment(textArea, Alignment.MIDDLE_CENTER);
			layout.setComponentAlignment(layoutButtons, Alignment.MIDDLE_RIGHT);
			layout.setExpandRatio(textArea, 3);
			
			layout.setSizeFull();
			
			
			subWindow.setContent(layout);
			subWindow.center();

			getUI().addWindow(subWindow);
		}
	};
	
	
ClickListener media2Listener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			String currentMedia = searchService.getMedia2();
			final TextArea textArea = new TextArea();
			textArea.setImmediate(true);
			textArea.addStyleName(ValoTheme.TEXTFIELD_SMALL);
			textArea.setValue(currentMedia);
			textArea.setNullRepresentation("");
			textArea.setInputPrompt("Insert Text Here");
			textArea.setColumns(30);
			textArea.setRows(10);
			final Window subWindow = new Window();
			subWindow.setModal(true);
			subWindow.setHeight("350px");
			subWindow.setWidth("500px");
			subWindow.setCaption("Default Text Media 2");
			subWindow.setStyleName(ValoTheme.WINDOW_TOP_TOOLBAR);
			subWindow.setClosable(false);
			subWindow.setResizable(false);
			
			HorizontalLayout layoutButtons = new HorizontalLayout();
			layoutButtons.setMargin(false);
			Button sendBtn = new Button("Save");
			sendBtn.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					try{
				
					String text = textArea.getValue();
					searchService.updateMedia2(text);
					subWindow.close();
					Notification.show("Default Text Updated");
					}catch(Exception e){
						
						e.printStackTrace();
					}
				}
			});
			sendBtn.setImmediate(true);
			sendBtn.setStyleName(ValoTheme.BUTTON_TINY);
			sendBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
			Button cancelBtn = new Button("Cancel");
			cancelBtn.setStyleName(ValoTheme.BUTTON_TINY);
			cancelBtn.addStyleName(ValoTheme.BUTTON_DANGER);
			cancelBtn.setImmediate(true);
			cancelBtn.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					subWindow.close();
					
				}
			});
			
			layoutButtons.setSizeUndefined();
			layoutButtons.setSpacing(true);
			layoutButtons.addComponents(cancelBtn, sendBtn);
			
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);
			layout.setMargin(true);
			layout.addComponent(textArea);
			layout.addComponent(layoutButtons);
			layout.setComponentAlignment(textArea, Alignment.MIDDLE_CENTER);
			layout.setComponentAlignment(layoutButtons, Alignment.MIDDLE_RIGHT);
			layout.setExpandRatio(textArea, 3);
			
			layout.setSizeFull();
			
			
			subWindow.setContent(layout);
			subWindow.center();

			getUI().addWindow(subWindow);
		}
	};
	
	
	
}
