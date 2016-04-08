package com.mechanicshop.components;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.mechanicshop.service.SearchService;
import com.mechanicshop.service.SmsSenderService;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import de.steinwedel.messagebox.ButtonOption;
import de.steinwedel.messagebox.MessageBox;
import tm.kod.widgets.numberfield.NumberField;

@SpringComponent
@UIScope
public class TableLayout extends VerticalLayout {

	@Autowired
	SearchService searchService;

	@Autowired
	SmsSenderService smsSenderService;

	@Autowired
	DataEntryLayout dataEntryLayout;
	
	SimpleJDBCConnectionPool connectionPool;
	Table table = new Table();

	Label titleLabel = new Label("CARS IN");
	Button btnSendSMS = new Button("Send SMS");
	TextField searchTextField = new TextField();
	SQLContainer container = null;
	final Set<Object> selectedItemIds = new HashSet<Object>();

	@PostConstruct
	void init() {
		addStyleName(ValoTheme.LAYOUT_WELL);
		setMargin(true);
		setSizeFull();
		buildLayout();
		customizeTable();
		try {
			connectionPool = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/t4l", "root",
					"");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void fillTable(String option) {
		selectedItemIds.clear();
		titleLabel.setValue(option.toUpperCase());
		try {
			switch (option) {
			case "Cars In":
				container = new SQLContainer(new TableQuery("cars_in", connectionPool));
				btnSendSMS.setVisible(false);
				container.setAutoCommit(true);
				searchTextField.clear();
				break;
			case "Cars Out":
				container = new SQLContainer(new TableQuery("cars_out", connectionPool));
				btnSendSMS.setVisible(false);
				container.setAutoCommit(true);
				searchTextField.clear();
				break;
			case "Cars Ready":
				container = new SQLContainer(new TableQuery("cars_ready", connectionPool));
				btnSendSMS.setVisible(true);
				container.setAutoCommit(true);
				searchTextField.clear();
				break;

			case "Cars Comeback":
				container = new SQLContainer(new TableQuery("cars_comeback", connectionPool));
				btnSendSMS.setVisible(true);
				container.setAutoCommit(true);
				searchTextField.clear();
				break;
	
			case "Cars Pending":
				container = new SQLContainer(new TableQuery("cars_pending", connectionPool));
				btnSendSMS.setVisible(true);
				container.setAutoCommit(true);
				searchTextField.clear();
				break;

				
			default:
				break;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table.setContainerDataSource(container);
		table.setColumnHeaders(new String[] { "No", "Tag", "Phone", "Name", "Vehicle", "License Plate", "Vin",
				"In Shop", "Out Shop", "Status", "Mileage", "Picked", "Payment", "Remarks", "Rebuilder", "Installer",
				"First Check By", "Second Check By", "First Check Date", "Second Check Date", "Media" , "Media 2", "Refered By",
				"Warranty Limit", "Warranty", "SMS Sent", "Comeback", "", " " });

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
		table.setVisibleColumns("", " ", "No", "Tag", "Phone", "Name", "Vehicle", "LicensePlate", "Vin", "InShop", "OutShop",
				"Status", "Mileage", "Picked", "Payment", "Remarks", "Rebuilder", "Installer", "FirstCheckBy",
				"SecondCheckBy", "FirstCheckDate", "SecondCheckDate", "Media", "Media2", "ReferedBy", "WarrantyLimit",
				"Warranty", "Comeback");
		if (option.equals("Cars Ready"))
			table.setVisibleColumns("", " ", "No", "Tag", "Phone", "Name", "Vehicle", "LicensePlate", "Vin", "InShop",
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
				// field.addValueChangeListener(textfieldListener);
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
			} 
			else {
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

	TextChangeListener filterChangeListener = new TextChangeListener() {
		@Override
		public void textChange(TextChangeEvent event) {
			String text = event.getText();

			container.removeAllContainerFilters();

			if (!text.isEmpty()) {

				container.addContainerFilter(new Or(new Like("Phone", "%" + text + "%", false),
						new Like("Name", "%" + text + "%", false), new Like("Vehicle", "%" + text + "%", false),
						new Like("LicensePlate", "%" + text + "%", false), new Like("Vin", "%" + text + "%", false),
						new Like("LicensePlate", "%" + text + "%", false), new Like("Status", "%" + text + "%", false),
						new Like("Picked", "%" + text + "%", false), new Like("Payment", "%" + text + "%", false),
						new Like("Remarks", "%" + text + "%", false), new Like("Rebuilder", "%" + text + "%", false),
						new Like("Installer", "%" + text + "%", false),
						new Like("FirstCheckBy", "%" + text + "%", false),
						new Like("SecondCheckBy", "%" + text + "%", false), new Like("Media", "%" + text + "%", false),
						new Like("ReferedBy", "%" + text + "%", false),
						new Like("Media2", "%" + text + "%", false),
						new Like("WarrantyLimit", "%" + text + "%", false),
						new Like("Warranty", "%" + text + "%", false), new Like("No", "%" + text + "%", false),
						new Like("Tag", "%" + text + "%", false), new Like("Mileage", "%" + text + "%", false))

				);
				int tableLength = table.getVisibleItemIds().size();

				if (tableLength < 1) {
					String table = searchService.findFilter(text);
					if (table != null)
						switch (table) {
						case "Out":
							fillTable("Cars Out");
							searchTextField.setValue(text);
							container.addContainerFilter(new Or(new Like("Phone", "%" + text + "%", false),
									new Like("Name", "%" + text + "%", false),
									new Like("Vehicle", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Vin", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Status", "%" + text + "%", false),
									new Like("Picked", "%" + text + "%", false),
									new Like("Payment", "%" + text + "%", false),
									new Like("Remarks", "%" + text + "%", false),
									new Like("Rebuilder", "%" + text + "%", false),
									new Like("Installer", "%" + text + "%", false),
									new Like("FirstCheckBy", "%" + text + "%", false),
									new Like("SecondCheckBy", "%" + text + "%", false),
									new Like("Media", "%" + text + "%", false),

									new Like("Media2", "%" + text + "%", false),
									new Like("ReferedBy", "%" + text + "%", false),
									new Like("WarrantyLimit", "%" + text + "%", false),
									new Like("Warranty", "%" + text + "%", false),
									new Like("No", "%" + text + "%", false), new Like("Tag", "%" + text + "%", false),
									new Like("Mileage", "%" + text + "%", false))

							);
							break;
						case "In":
							fillTable("Cars In");
							searchTextField.setValue(text);
							container.addContainerFilter(new Or(new Like("Phone", "%" + text + "%", false),
									new Like("Name", "%" + text + "%", false),
									new Like("Vehicle", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Vin", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Status", "%" + text + "%", false),
									new Like("Picked", "%" + text + "%", false),
									new Like("Payment", "%" + text + "%", false),
									new Like("Remarks", "%" + text + "%", false),
									new Like("Rebuilder", "%" + text + "%", false),
									new Like("Installer", "%" + text + "%", false),
									new Like("FirstCheckBy", "%" + text + "%", false),
									new Like("SecondCheckBy", "%" + text + "%", false),
									new Like("Media", "%" + text + "%", false),

									new Like("Media2", "%" + text + "%", false),
									new Like("ReferedBy", "%" + text + "%", false),
									new Like("WarrantyLimit", "%" + text + "%", false),
									new Like("Warranty", "%" + text + "%", false),
									new Like("No", "%" + text + "%", false), new Like("Tag", "%" + text + "%", false),
									new Like("Mileage", "%" + text + "%", false))

							);
							break;
						case "Ready":
							fillTable("Cars Ready");
							searchTextField.setValue(text);
							container.addContainerFilter(new Or(new Like("Phone", "%" + text + "%", false),
									new Like("Name", "%" + text + "%", false),
									new Like("Vehicle", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Vin", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Status", "%" + text + "%", false),
									new Like("Picked", "%" + text + "%", false),
									new Like("Payment", "%" + text + "%", false),
									new Like("Remarks", "%" + text + "%", false),
									new Like("Rebuilder", "%" + text + "%", false),
									new Like("Installer", "%" + text + "%", false),
									new Like("FirstCheckBy", "%" + text + "%", false),
									new Like("SecondCheckBy", "%" + text + "%", false),
									new Like("Media", "%" + text + "%", false),
									new Like("Media2", "%" + text + "%", false),
									new Like("ReferedBy", "%" + text + "%", false),
									new Like("WarrantyLimit", "%" + text + "%", false),
									new Like("Warranty", "%" + text + "%", false),
									new Like("No", "%" + text + "%", false), new Like("Tag", "%" + text + "%", false),
									new Like("Mileage", "%" + text + "%", false))

							);
							break;
						
						case "Pending":
							fillTable("Cars Pending");
							searchTextField.setValue(text);
							container.addContainerFilter(new Or(new Like("Phone", "%" + text + "%", false),
									new Like("Name", "%" + text + "%", false),
									new Like("Vehicle", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Vin", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Status", "%" + text + "%", false),
									new Like("Picked", "%" + text + "%", false),
									new Like("Payment", "%" + text + "%", false),
									new Like("Remarks", "%" + text + "%", false),
									new Like("Rebuilder", "%" + text + "%", false),
									new Like("Installer", "%" + text + "%", false),
									new Like("FirstCheckBy", "%" + text + "%", false),
									new Like("SecondCheckBy", "%" + text + "%", false),
									new Like("Media", "%" + text + "%", false),
									new Like("Media2", "%" + text + "%", false),
									new Like("ReferedBy", "%" + text + "%", false),
									new Like("WarrantyLimit", "%" + text + "%", false),
									new Like("Warranty", "%" + text + "%", false),
									new Like("No", "%" + text + "%", false), new Like("Tag", "%" + text + "%", false),
									new Like("Mileage", "%" + text + "%", false))

							);
							break;
						case "Comeback":
							fillTable("Cars Comeback");
							searchTextField.setValue(text);
							container.addContainerFilter(new Or(new Like("Phone", "%" + text + "%", false),
									new Like("Name", "%" + text + "%", false),
									new Like("Vehicle", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Vin", "%" + text + "%", false),
									new Like("LicensePlate", "%" + text + "%", false),
									new Like("Status", "%" + text + "%", false),
									new Like("Picked", "%" + text + "%", false),
									new Like("Payment", "%" + text + "%", false),
									new Like("Remarks", "%" + text + "%", false),
									new Like("Rebuilder", "%" + text + "%", false),
									new Like("Installer", "%" + text + "%", false),
									new Like("FirstCheckBy", "%" + text + "%", false),
									new Like("SecondCheckBy", "%" + text + "%", false),
									new Like("Media", "%" + text + "%", false),
									new Like("Media2", "%" + text + "%", false),
									new Like("ReferedBy", "%" + text + "%", false),
									new Like("WarrantyLimit", "%" + text + "%", false),
									new Like("Warranty", "%" + text + "%", false),
									new Like("No", "%" + text + "%", false), new Like("Tag", "%" + text + "%", false),
									new Like("Mileage", "%" + text + "%", false))

							);
							break;
						default:

							break;
						}

				}
			}

		}

	};



	private ClickListener addBtnListener = new ClickListener() {

		@Override
		public void buttonClick(ClickEvent event) {
			container.addItem();
			try {

				container.commit();
			} catch (UnsupportedOperationException | SQLException e) {

				e.printStackTrace();
			}

		}
	};

	private ClickListener sendSMSBtnListener = new ClickListener() {

		@Override
		public void buttonClick(ClickEvent event) {

			MessageBox.createQuestion().withCaption("Send SMS")
					.withMessage("Do you want to send the default messages or write a new one?")
					.withYesButton(new Runnable() {

				@Override
				public void run() {
					smsSenderService.sendMessagesMassive();
					Notification.show("Messages Sent");

				}
			}, ButtonOption.caption("Default"), ButtonOption.icon(null)).withNoButton(new Runnable() {

				@Override
				public void run() {
					createCustomMessage();

				}
			}, ButtonOption.caption("Custom"), ButtonOption.icon(null)).open();

		}
	};

	private ClickListener removeListener = new ClickListener() {

		@Override
		public void buttonClick(ClickEvent event) {
			int selectedItems = selectedItemIds.size();
			if (selectedItems > 0) {

				MessageBox.createQuestion().withCaption("Confirm")
						.withMessage("Do you want to remove " + selectedItems + " car(s)?")
						.withYesButton(new Runnable() {

					@Override
					public void run() {
						for (Object object : selectedItemIds) {

							container.removeItem(object);

						}
						try {
							container.commit();
						} catch (UnsupportedOperationException | SQLException e) {

							e.printStackTrace();
						}
						selectedItemIds.clear();

					}
				}).withNoButton().open();

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

		layoutTable.addComponent(table);
		layoutTable.setComponentAlignment(table, Alignment.TOP_CENTER);
		layoutTable.setSizeFull();

		layoutTable.setSpacing(true);
		HorizontalLayout layoutButtons = new HorizontalLayout();
		layoutButtons.setMargin(false);
		layoutButtons.setSpacing(true);
		layoutButtons.setSizeUndefined();
		layoutButtons.setWidth("100%");
		Button addBtn = new Button("Add new Car");
		addBtn.addClickListener(addBtnListener);
		addBtn.setImmediate(true);
		addBtn.setStyleName(ValoTheme.BUTTON_TINY);
		addBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		Button deleteBtn = new Button("Delete Selected");
		deleteBtn.setStyleName(ValoTheme.BUTTON_TINY);
		deleteBtn.addStyleName(ValoTheme.BUTTON_DANGER);
		deleteBtn.setImmediate(true);
		deleteBtn.addClickListener(removeListener);

		btnSendSMS.setStyleName(ValoTheme.BUTTON_TINY);
		btnSendSMS.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		btnSendSMS.setImmediate(true);
		btnSendSMS.addClickListener(sendSMSBtnListener);

		searchTextField.setImmediate(true);
		searchTextField.addStyleName(ValoTheme.TEXTFIELD_TINY);
		searchTextField.addTextChangeListener(filterChangeListener);
		Label lbSearch = new Label("Search");
		lbSearch.addStyleName(ValoTheme.LABEL_TINY);
		lbSearch.setSizeUndefined();
		layoutButtons.addComponents(lbSearch, searchTextField, addBtn, deleteBtn, btnSendSMS);

		layoutButtons.setComponentAlignment(lbSearch, Alignment.MIDDLE_LEFT);
		layoutButtons.setComponentAlignment(searchTextField, Alignment.BOTTOM_LEFT);
		layoutButtons.setComponentAlignment(addBtn, Alignment.BOTTOM_RIGHT);
		layoutButtons.setComponentAlignment(deleteBtn, Alignment.BOTTOM_RIGHT);
		layoutButtons.setComponentAlignment(btnSendSMS, Alignment.BOTTOM_RIGHT);
		layoutButtons.setExpandRatio(addBtn, 3);
		addComponent(layoutTitle);
		addComponent(layoutTable);
		layoutTable.addComponent(layoutButtons);
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
		table.setSizeFull();
		table.addGeneratedColumn("", new Table.ColumnGenerator() {

			@Override
			public Object generateCell(Table source, final Object itemId, Object columnId) {
				boolean selected = false;

				final CheckBox cb = new CheckBox("", selected);

				cb.addValueChangeListener(new Property.ValueChangeListener() {

					public void valueChange(ValueChangeEvent event) {
						if (selectedItemIds.contains(itemId)) {
							selectedItemIds.remove(itemId);
						} else {
							if (cb.getValue() != false) {
								selectedItemIds.add(itemId);
							}
						}
					}
				});
				return cb;
			}
		});
		
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
	
	public void createCustomMessage(){
		final TextArea textArea = new TextArea();
		textArea.setImmediate(true);
		textArea.setColumns(30);
		textArea.setRows(10);
		textArea.addStyleName(ValoTheme.TEXTAREA_SMALL);
		textArea.setRequired(true);
		final Window subWindow = new Window();
		subWindow.setModal(true);
		subWindow.setHeight("350px");
		subWindow.setWidth("500px");
		subWindow.setCaption("Insert Message");
		subWindow.setStyleName(ValoTheme.WINDOW_TOP_TOOLBAR);
		subWindow.setClosable(false);
		subWindow.setResizable(false);
		
		HorizontalLayout layoutButtons = new HorizontalLayout();
		layoutButtons.setMargin(false);
		Button sendBtn = new Button("Send");
		sendBtn.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				try{
				textArea.validate();
				String message = textArea.getValue();
				smsSenderService.sendMessageMassive(message);
				subWindow.close();
				Notification.show("Message Sent");
				}catch(Exception e){
					
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
	
	
	
	

	
}
