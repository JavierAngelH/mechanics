package com.mechanicshop.components;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.mechanicshop.utils.Utilities;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import tm.kod.widgets.numberfield.NumberField;

@SpringComponent
@UIScope
public class MaintenanceLayout extends VerticalLayout {
	
	SimpleJDBCConnectionPool connectionPool;
	Table table = new Table();
	Label titleLabel = new Label("MAINTENANCE");
	SQLContainer container = null;
	

	@Autowired
	DataEntryLayout dataEntryLayout;
	
	@PostConstruct
	void init() {
		addStyleName(ValoTheme.LAYOUT_WELL);
		setMargin(true);
		setSizeFull();
		buildLayout();
		customizeTable();
		
		try {
			connectionPool = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/t4l", "root",
					"1234");
			fillTable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void fillTable() {
		
		try {
				container = new SQLContainer(new TableQuery("cars_unknown", connectionPool));
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		table.setColumnWidth("InShop", 90);
		table.setColumnWidth("OutShop", 90);
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
		table.setColumnWidth("FirstCheckDate", 120);
		table.setColumnWidth("SecondCheckDate", 150);
		table.setColumnWidth("SMS", 85);
		table.setColumnWidth("Comeback", 85);
		table.setColumnWidth("DateDeleted", 180);
		table.setVisibleColumns(" ",  "No", "DateDeleted", "Tag", "Phone", "Name", "Vehicle", "LicensePlate", "Vin", "InShop",
					"OutShop", "Status", "Mileage", "Picked", "Payment", "Remarks", "Rebuilder", "Installer",
					"FirstCheckBy", "SecondCheckBy", "FirstCheckDate", "SecondCheckDate", "Media", "Media2", "ReferedBy",
					"WarrantyLimit", "Warranty", "Comeback", "SMS");
		table.setTableFieldFactory(tableFactory);

	}

	

	private TableFieldFactory tableFactory = new TableFieldFactory() {

		@Override
		public Field<?> createField(Container fieldContainer, final Object itemId, Object propertyId,
				Component uiContext) {
			String column = (String) propertyId;
			if (column.equals("InShop") || column.equals("OutShop") || column.equals("FirstCheckDate")
					|| column.equals("SecondCheckDate")) {

				PopupDateField field = new PopupDateField(column);
				field.setData(itemId);
				field.setImmediate(true);
				field.addStyleName(ValoTheme.DATEFIELD_TINY);
				field.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
				field.setInputPrompt("Select");
				field.setTextFieldEnabled(false);
				return field;
			} else if (column.equals("Tag") || column.equals("Mileage")) {
				NumberField field = new NumberField(column);
				field.setData(itemId);
				field.setImmediate(true);
				field.setSigned(false);

				field.setNullRepresentation("");
				field.addStyleName(ValoTheme.TEXTFIELD_TINY);
				field.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
				return field;
			} else if (column.equals("Status")) {
				NativeSelect field = new NativeSelect();
				field.addItems("In", "Out", "Pending", "Ready", "Comeback");
				field.setImmediate(true);
				field.addValueChangeListener(new ValueChangeListener() {

					@Override
					public void valueChange(ValueChangeEvent event) {
						String newValue = (String) event.getProperty().getValue();
						String currentTable = titleLabel.getValue();
						if (newValue != null) {
							if (!currentTable.contains(newValue.toUpperCase())) {
								container.removeItem(itemId);
							}

						}
					}
				});
				return field;
			} else if (column.equals("Warranty")) {
				NativeSelect field = new NativeSelect();
				field.addItems("Yes", "No");
				field.setImmediate(true);
				return field;
			} else if (column.equals("SMS")) {
				NativeSelect field = new NativeSelect();
				field.addItems("YES", "NO");
				field.setImmediate(true);
				return field;
			} 
			else if (column.equals("Comeback")) {
				NativeSelect field = new NativeSelect();
				field.addItems("YES", "NO");
				field.setImmediate(true);
				return field;
			} else if (column.equals("DateDeleted")) {

				PopupDateField field = new PopupDateField(column);
				field.setDateFormat("yyyy/MM/dd hh:mm a");
				field.setData(itemId);
				field.setImmediate(true);
				field.addStyleName(ValoTheme.DATEFIELD_TINY);
				field.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
				field.setInputPrompt("Select");
				field.setTextFieldEnabled(false);
				return field;
			} else {
				TextField field = new TextField(column);
				field.setData(itemId);
				field.setNullRepresentation("");
				field.setImmediate(true);
				field.addStyleName(ValoTheme.TEXTFIELD_TINY);
				field.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
				// field.addValueChangeListener(textfieldListener);
				return field;
			} 

		}
	};
	
	
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
		layoutButtons.setWidth("100%");
		Button addBtn = new Button("Add new Car");
		//addBtn.addClickListener(addBtnListener);
		addBtn.setImmediate(true);
		addBtn.setStyleName(ValoTheme.BUTTON_TINY);
		addBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		Button deleteBtn = new Button("Delete Selected");
		deleteBtn.setStyleName(ValoTheme.BUTTON_TINY);
		deleteBtn.addStyleName(ValoTheme.BUTTON_DANGER);
		deleteBtn.setImmediate(true);
		//deleteBtn.addClickListener(removeListener);

	
	
		Label lbSearch = new Label("Search");
		lbSearch.addStyleName(ValoTheme.LABEL_TINY);
		lbSearch.setSizeUndefined();
		layoutButtons.addComponents(lbSearch, addBtn, deleteBtn);

		layoutButtons.setComponentAlignment(lbSearch, Alignment.MIDDLE_LEFT);
		layoutButtons.setComponentAlignment(addBtn, Alignment.BOTTOM_RIGHT);
		layoutButtons.setComponentAlignment(deleteBtn, Alignment.BOTTOM_RIGHT);
		layoutButtons.setExpandRatio(addBtn, 3);
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
		final Window subWindow = new Window();
		subWindow.setModal(true);
		subWindow.setHeight("98%");
		subWindow.setWidth("98%");
		subWindow.setCaption("Edit Entry");
		subWindow.setStyleName(ValoTheme.WINDOW_TOP_TOOLBAR);
		subWindow.setClosable(true);
		subWindow.setResizable(false);
		dataEntryLayout.fillDataEntry(item);
		subWindow.setContent(dataEntryLayout);
		subWindow.center();
		getUI().addWindow(subWindow);
	}
	

}
