package com.mechanicshop.components;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.mechanicshop.service.SearchService;
import com.vaadin.data.Item;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import tm.kod.widgets.numberfield.NumberField;

@SpringComponent
@UIScope
public class DataEntryLayout extends Window {

	VerticalLayout mainLayout = new VerticalLayout();
	HorizontalLayout hLayoutForms = new HorizontalLayout();

	final NumberField tfTag = new NumberField("Tag");
	final TextField tfPhone = new TextField("Phone");
	final TextField tfName = new TextField("Name");
	final TextField tfVehicle = new TextField("Vehicle");
	final TextField tfLicensePlate = new TextField("License Plate");
	final TextField tfVin = new TextField("Vin");
	final PopupDateField dfInShop = new PopupDateField("In Shop");
	final PopupDateField dfOutShop = new PopupDateField("Out Shop");
	final NativeSelect nsStatus = new NativeSelect("Status");
	final NumberField tfMileage = new NumberField("Mileage");
	final TextField tfPicked = new TextField("Picked");
	final TextField tfPayment = new TextField("Payment");
	final TextArea taRemarks = new TextArea("Remarks");
	final TextField tfRebuilder = new TextField("Rebuilder");
	final TextField tfInstaller = new TextField("Installer");
	final TextField tfFirstCheckBy = new TextField("First Check By");
	final TextField tfSecondCheckBy = new TextField("Second Check By");
	final PopupDateField dfFirstCheckDate = new PopupDateField("First Check Date");
	final PopupDateField dfSecondCheckDate = new PopupDateField("Second Check Date");
	final TextArea taMedia = new TextArea("Media");
	final TextArea taMedia2 = new TextArea("Media 2");
	final TextField tfReferedBy = new TextField("Refered By");
	final TextField tfWarrantyLimit = new TextField("Warranty Limit");
	final NativeSelect nsWarranty = new NativeSelect("Warranty");
	final NativeSelect nsSMS = new NativeSelect("SMS");
	final NativeSelect nsComeback = new NativeSelect("Comeback");
	public Button sendBtn = new Button("Add");
	String tableName;
	Integer no;
	@Autowired
	SearchService searchService;

	@PostConstruct
	void init() {
		addStyleName(ValoTheme.LAYOUT_CARD);
		mainLayout.setMargin(true);
		setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setSizeUndefined();
		buildLayout();
		setContent(mainLayout);
		setModal(true);
		setHeight("98%");
		setWidth("98%");
		setCaption("Add Entry");
		setStyleName(ValoTheme.WINDOW_TOP_TOOLBAR);
		setClosable(true);
		setResizable(true);
		center();
	}

	private void buildLayout() {
		FormLayout formLayout1 = new FormLayout();

		formLayout1.setMargin(new MarginInfo(false, false, false, true));
		formLayout1.setSpacing(true);

		FormLayout formLayout2 = new FormLayout();
		formLayout2.setMargin(new MarginInfo(false, false, false, true));
		formLayout2.setSpacing(true);

		FormLayout formLayout3 = new FormLayout();
		formLayout3.setMargin(new MarginInfo(false, true, false, true));
		formLayout3.setSpacing(true);

		tfTag.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfTag.setWidth("180px");
		tfTag.setMaxLength(3);
		tfTag.setNullRepresentation("");
		tfPhone.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfPhone.setWidth("180px");
		tfPhone.setNullRepresentation("");
		tfPhone.setMaxLength(11);
		tfName.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfName.setWidth("180px");
		tfName.setMaxLength(50);
		tfName.setNullRepresentation("");
		tfVehicle.setWidth("180px");
		tfVehicle.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfVehicle.setMaxLength(75);
		tfVehicle.setNullRepresentation("");
		tfLicensePlate.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfLicensePlate.setWidth("180px");
		tfLicensePlate.setMaxLength(10);
		tfLicensePlate.setNullRepresentation("");
		tfVin.setWidth("180px");
		tfVin.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfVin.setMaxLength(17);
		tfVin.setNullRepresentation("");
		dfInShop.setImmediate(true);
		dfInShop.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfInShop.setInputPrompt("Select");
		dfInShop.setWidth("120px");

		dfOutShop.setImmediate(true);
		dfOutShop.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfOutShop.setInputPrompt("Select");
		dfOutShop.setWidth("120px");

		nsStatus.addItems("In", "Out", "Pending", "Ready", "Comeback");
		nsStatus.setImmediate(true);
		nsStatus.setWidth("120px");
		nsStatus.setStyleName(ValoTheme.COMBOBOX_TINY);
		nsStatus.setValue("In");
		tfMileage.setWidth("120px");
		tfMileage.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfMileage.setMaxLength(6);
		tfMileage.setNullRepresentation("");
		tfPicked.setWidth("120px");
		tfPicked.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfPicked.setMaxLength(3);
		tfPicked.setNullRepresentation("");
		tfPayment.setWidth("180px");
		tfPayment.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfPayment.setMaxLength(50);
		tfPayment.setNullRepresentation("");
		taRemarks.setWidth("500px");
		taRemarks.setStyleName(ValoTheme.TEXTFIELD_TINY);
		taRemarks.setMaxLength(500);
		taRemarks.setNullRepresentation("");
		tfRebuilder.setWidth("180px");
		tfRebuilder.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfRebuilder.setMaxLength(20);
		tfRebuilder.setNullRepresentation("");
		tfInstaller.setWidth("180px");
		tfInstaller.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfInstaller.setMaxLength(20);
		tfInstaller.setNullRepresentation("");
		tfFirstCheckBy.setWidth("500px");
		tfFirstCheckBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfFirstCheckBy.setMaxLength(100);
		tfFirstCheckBy.setNullRepresentation("");
		tfSecondCheckBy.setWidth("500px");
		tfSecondCheckBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfSecondCheckBy.setMaxLength(100);
		tfSecondCheckBy.setNullRepresentation("");
		dfFirstCheckDate.setImmediate(true);
		dfFirstCheckDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfFirstCheckDate.setInputPrompt("Select");
		dfFirstCheckDate.setWidth("120px");

		dfSecondCheckDate.setImmediate(true);
		dfSecondCheckDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfSecondCheckDate.setInputPrompt("Select");
		dfSecondCheckDate.setWidth("120px");

		taMedia.setWidth("500px");
		taMedia.setStyleName(ValoTheme.TEXTFIELD_TINY);
		taMedia.setNullRepresentation("");
		taMedia2.setWidth("500px");
		taMedia2.setStyleName(ValoTheme.TEXTFIELD_TINY);
		taMedia2.setNullRepresentation("");
		tfReferedBy.setWidth("180px");
		tfReferedBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfReferedBy.setMaxLength(20);
		tfReferedBy.setNullRepresentation("");
		tfWarrantyLimit.setWidth("180px");
		tfWarrantyLimit.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfWarrantyLimit.setMaxLength(5);
		tfWarrantyLimit.setNullRepresentation("");
		nsWarranty.setWidth("120px");
		nsWarranty.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsWarranty.addItems("YES", "NO");
		nsWarranty.setValue("NO");
		nsWarranty.setStyleName(ValoTheme.COMBOBOX_TINY);

		nsSMS.setWidth("120px");
		nsSMS.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsSMS.addItems("YES", "NO");
		nsSMS.setValue("NO");
		nsComeback.setWidth("120px");
		nsComeback.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsComeback.addItems("YES", "NO");
		nsComeback.setValue("NO");
		nsComeback.setStyleName(ValoTheme.COMBOBOX_TINY);
		
		formLayout1.addComponents(tfTag, tfPhone, tfName, tfVehicle, tfPayment, tfRebuilder, tfInstaller,
				tfLicensePlate, tfVin, tfReferedBy, tfWarrantyLimit);

		formLayout2.addComponents(nsWarranty, dfInShop, dfOutShop, nsStatus, nsSMS, nsComeback, tfMileage, tfPicked,
				dfFirstCheckDate, dfSecondCheckDate);

		formLayout3.addComponents(tfFirstCheckBy, tfSecondCheckBy, taRemarks, taMedia, taMedia2);
		
		HorizontalLayout layoutButtons = new HorizontalLayout();
		layoutButtons.setMargin(new MarginInfo(false, true, false, true));

		sendBtn.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				Integer tag = null;
				Integer mileage = null;
				if (tfTag.getValue() != null)
					if (!tfTag.getValue().isEmpty()) {
						tag = (Integer.parseInt(tfTag.getValue()));
					} else
						tag = null;

				if (tfMileage.getValue() != null)
					if (!tfMileage.getValue().isEmpty()) {
						mileage = (Integer.parseInt(tfMileage.getValue()));
					} else
						mileage = null;

				Object[] args = new Object[] { tag, tfPhone.getValue(), tfName.getValue(), tfVehicle.getValue(),
						tfLicensePlate.getValue(), tfVin.getValue(), dfInShop.getValue(), dfOutShop.getValue(),
						nsStatus.getValue(), mileage, tfPicked.getValue(), tfPayment.getValue(), taRemarks.getValue(),
						tfRebuilder.getValue(), tfInstaller.getValue(), tfFirstCheckBy.getValue(),
						tfSecondCheckBy.getValue(), dfFirstCheckDate.getValue(), dfSecondCheckDate.getValue(),
						taMedia.getValue(), taMedia2.getValue(), tfReferedBy.getValue(), tfWarrantyLimit.getValue(),
						nsWarranty.getValue(), nsSMS.getValue(), nsComeback.getValue() };
				
				if (sendBtn.getCaption().equals("Add")) {
					try {
						String status = nsStatus.getValue().toString();
						searchService.insertCar(args, status);
						Notification.show("Entry inserted succesfully");
						close();
					} catch (Exception e) {
						e.printStackTrace();
						Notification.show("An error has occurred", Notification.Type.ERROR_MESSAGE);
					}
				} else {
					try {
						searchService.editCar(args, tableName, no);
						Notification.show("Entry edited succesfully");
						close();
					} catch (Exception e) {
						e.printStackTrace();
						Notification.show("An error has occurred", Notification.Type.ERROR_MESSAGE);
					}

				}
			}

		});
		sendBtn.setImmediate(true);
		sendBtn.setStyleName(ValoTheme.BUTTON_TINY);
		sendBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);

		layoutButtons.setSizeUndefined();
		layoutButtons.setSpacing(true);
		layoutButtons.addComponents(sendBtn);

		hLayoutForms.addComponents(formLayout1, formLayout2, formLayout3);

		mainLayout.addComponent(hLayoutForms);
		mainLayout.addComponent(layoutButtons);
		mainLayout.setComponentAlignment(hLayoutForms, Alignment.MIDDLE_CENTER);
		mainLayout.setComponentAlignment(layoutButtons, Alignment.MIDDLE_LEFT);
		mainLayout.setExpandRatio(hLayoutForms, 3);

	}

	public void fillDataEntry(Item item, String tableName) {
		this.no = Integer.parseInt(item.getItemProperty("No").toString());
		switch (tableName) {
		case "CARS IN":
			this.tableName = "cars_in";
			sendBtn.setCaption("Save");

			sendBtn.setVisible(true);
			setCaption("Edit Entry");
			tfFirstCheckBy.setEnabled(true);
			tfInstaller.setEnabled(true);
			taMedia.setEnabled(true);
			taMedia2.setEnabled(true);
			taRemarks.setEnabled(true);
			tfLicensePlate.setEnabled(true);
			tfMileage.setEnabled(true);
			tfName.setEnabled(true);
			tfPayment.setEnabled(true);
			tfPhone.setEnabled(true);
			tfPicked.setEnabled(true);
			tfRebuilder.setEnabled(true);
			tfReferedBy.setEnabled(true);
			tfSecondCheckBy.setEnabled(true);
			tfTag.setEnabled(true);
			tfVehicle.setEnabled(true);
			tfVin.setEnabled(true);
			tfWarrantyLimit.setEnabled(true);
			nsComeback.setEnabled(true);
			nsSMS.setEnabled(true);
			nsStatus.setEnabled(true);
			nsWarranty.setEnabled(true);
			dfFirstCheckDate.setEnabled(true);
			dfInShop.setEnabled(true);
			dfOutShop.setEnabled(true);
			dfSecondCheckDate.setEnabled(true);
			break;
		case "CARS OUT":
			this.tableName ="cars_out";
			sendBtn.setCaption("Save");

			sendBtn.setVisible(true);
			setCaption("Edit Entry");
			tfFirstCheckBy.setEnabled(true);
			tfInstaller.setEnabled(true);
			taMedia.setEnabled(true);
			taMedia2.setEnabled(true);
			taRemarks.setEnabled(true);
			tfLicensePlate.setEnabled(true);
			tfMileage.setEnabled(true);
			tfName.setEnabled(true);
			tfPayment.setEnabled(true);
			tfPhone.setEnabled(true);
			tfPicked.setEnabled(true);
			tfRebuilder.setEnabled(true);
			tfReferedBy.setEnabled(true);
			tfSecondCheckBy.setEnabled(true);
			tfTag.setEnabled(true);
			tfVehicle.setEnabled(true);
			tfVin.setEnabled(true);
			tfWarrantyLimit.setEnabled(true);
			nsComeback.setEnabled(true);
			nsSMS.setEnabled(true);
			nsStatus.setEnabled(true);
			nsWarranty.setEnabled(true);
			dfFirstCheckDate.setEnabled(true);
			dfInShop.setEnabled(true);
			dfOutShop.setEnabled(true);
			dfSecondCheckDate.setEnabled(true);
			break;
		case "CARS READY":
			this.tableName = "cars_ready";
			sendBtn.setCaption("Save");

			sendBtn.setVisible(true);
			setCaption("Edit Entry");
			tfFirstCheckBy.setEnabled(true);
			tfInstaller.setEnabled(true);
			taMedia.setEnabled(true);
			taMedia2.setEnabled(true);
			taRemarks.setEnabled(true);
			tfLicensePlate.setEnabled(true);
			tfMileage.setEnabled(true);
			tfName.setEnabled(true);
			tfPayment.setEnabled(true);
			tfPhone.setEnabled(true);
			tfPicked.setEnabled(true);
			tfRebuilder.setEnabled(true);
			tfReferedBy.setEnabled(true);
			tfSecondCheckBy.setEnabled(true);
			tfTag.setEnabled(true);
			tfVehicle.setEnabled(true);
			tfVin.setEnabled(true);
			tfWarrantyLimit.setEnabled(true);
			nsComeback.setEnabled(true);
			nsSMS.setEnabled(true);
			nsStatus.setEnabled(true);
			nsWarranty.setEnabled(true);
			dfFirstCheckDate.setEnabled(true);
			dfInShop.setEnabled(true);
			dfOutShop.setEnabled(true);
			dfSecondCheckDate.setEnabled(true);
			break;

		case "CARS COMEBACK":
			this.tableName = "cars_comeback";
			sendBtn.setCaption("Save");

			sendBtn.setVisible(true);
			setCaption("Edit Entry");
			tfFirstCheckBy.setEnabled(true);
			tfInstaller.setEnabled(true);
			taMedia.setEnabled(true);
			taMedia2.setEnabled(true);
			taRemarks.setEnabled(true);
			tfLicensePlate.setEnabled(true);
			tfMileage.setEnabled(true);
			tfName.setEnabled(true);
			tfPayment.setEnabled(true);
			tfPhone.setEnabled(true);
			tfPicked.setEnabled(true);
			tfRebuilder.setEnabled(true);
			tfReferedBy.setEnabled(true);
			tfSecondCheckBy.setEnabled(true);
			tfTag.setEnabled(true);
			tfVehicle.setEnabled(true);
			tfVin.setEnabled(true);
			tfWarrantyLimit.setEnabled(true);
			nsComeback.setEnabled(true);
			nsSMS.setEnabled(true);
			nsStatus.setEnabled(true);
			nsWarranty.setEnabled(true);
			dfFirstCheckDate.setEnabled(true);
			dfInShop.setEnabled(true);
			dfOutShop.setEnabled(true);
			dfSecondCheckDate.setEnabled(true);
			break;

		case "CARS PENDING":
			this.tableName = "cars_pending";
			sendBtn.setCaption("Save");
			sendBtn.setVisible(true);
			setCaption("Edit Entry");
			tfFirstCheckBy.setEnabled(true);
			tfInstaller.setEnabled(true);
			taMedia.setEnabled(true);
			taMedia2.setEnabled(true);
			taRemarks.setEnabled(true);
			tfLicensePlate.setEnabled(true);
			tfMileage.setEnabled(true);
			tfName.setEnabled(true);
			tfPayment.setEnabled(true);
			tfPhone.setEnabled(true);
			tfPicked.setEnabled(true);
			tfRebuilder.setEnabled(true);
			tfReferedBy.setEnabled(true);
			tfSecondCheckBy.setEnabled(true);
			tfTag.setEnabled(true);
			tfVehicle.setEnabled(true);
			tfVin.setEnabled(true);
			tfWarrantyLimit.setEnabled(true);
			nsComeback.setEnabled(true);
			nsSMS.setEnabled(true);
			nsStatus.setEnabled(true);
			nsWarranty.setEnabled(true);
			dfFirstCheckDate.setEnabled(true);
			dfInShop.setEnabled(true);
			dfOutShop.setEnabled(true);
			dfSecondCheckDate.setEnabled(true);
			break;
			default:
				this.tableName = tableName;
				sendBtn.setVisible(false);
				setCaption("Entry Details");
				tfFirstCheckBy.setEnabled(false);
				tfInstaller.setEnabled(false);
				taMedia.setEnabled(false);
				taMedia2.setEnabled(false);
				taRemarks.setEnabled(false);
				tfLicensePlate.setEnabled(false);
				tfMileage.setEnabled(false);
				tfName.setEnabled(false);
				tfPayment.setEnabled(false);
				tfPhone.setEnabled(false);
				tfPicked.setEnabled(false);
				tfRebuilder.setEnabled(false);
				tfReferedBy.setEnabled(false);
				tfSecondCheckBy.setEnabled(false);
				tfTag.setEnabled(false);
				tfVehicle.setEnabled(false);
				tfVin.setEnabled(false);
				tfWarrantyLimit.setEnabled(false);
				nsComeback.setEnabled(false);
				nsSMS.setEnabled(false);
				nsStatus.setEnabled(false);
				nsWarranty.setEnabled(false);
				dfFirstCheckDate.setEnabled(false);
				dfInShop.setEnabled(false);
				dfOutShop.setEnabled(false);
				dfSecondCheckDate.setEnabled(false);

		break;}
		
		
		tfFirstCheckBy.setValue(item.getItemProperty("FirstCheckBy").toString());
		tfInstaller.setValue(item.getItemProperty("Installer").toString());
		taMedia.setValue(item.getItemProperty("Media").toString());
		taMedia2.setValue(item.getItemProperty("Media2").toString());
		taRemarks.setValue(item.getItemProperty("Remarks").toString());
		tfLicensePlate.setValue(item.getItemProperty("LicensePlate").toString());
		tfMileage.setValue(item.getItemProperty("Mileage").toString());
		tfName.setValue(item.getItemProperty("Name").toString());
		tfPayment.setValue(item.getItemProperty("Payment").toString());
		tfPhone.setValue(item.getItemProperty("Phone").toString());
		tfPicked.setValue(item.getItemProperty("Picked").toString());
		tfRebuilder.setValue(item.getItemProperty("Rebuilder").toString());
		tfReferedBy.setValue(item.getItemProperty("ReferedBy").toString());
		tfSecondCheckBy.setValue(item.getItemProperty("SecondCheckBy").toString());
		tfTag.setValue(item.getItemProperty("Tag").toString());
		tfVehicle.setValue(item.getItemProperty("Vehicle").toString());
		tfVin.setValue(item.getItemProperty("Vin").toString());
		tfWarrantyLimit.setValue(item.getItemProperty("WarrantyLimit").toString());
		nsComeback.setValue(item.getItemProperty("Comeback").toString());
		nsSMS.setValue(item.getItemProperty("SMS").toString());
		nsStatus.setValue(item.getItemProperty("Status").toString());
		nsWarranty.setValue(item.getItemProperty("Warranty").toString());
		dfFirstCheckDate.setValue((Date) item.getItemProperty("FirstCheckDate").getValue());
		dfInShop.setValue((Date) item.getItemProperty("InShop").getValue());
		dfOutShop.setValue((Date) item.getItemProperty("OutShop").getValue());
		dfSecondCheckDate.setValue((Date) item.getItemProperty("SecondCheckDate").getValue());
	}

	public void emptyEntry() {

		sendBtn.setCaption("Add");
		setCaption("Add Entry");
		tfFirstCheckBy.setValue(null);
		tfInstaller.setValue(null);
		taMedia.setValue(null);
		taMedia2.setValue(null);
		taRemarks.setValue(null);
		tfLicensePlate.setValue(null);
		tfMileage.setValue(null);
		tfName.setValue(null);
		tfPayment.setValue(null);
		tfPhone.setValue(null);
		tfPicked.setValue(null);
		tfRebuilder.setValue(null);
		tfReferedBy.setValue(null);
		tfSecondCheckBy.setValue(null);
		tfTag.setValue(null);
		tfVehicle.setValue(null);
		tfVin.setValue(null);
		tfWarrantyLimit.setValue(null);
		nsComeback.setValue("NO");
		nsSMS.setValue("NO");
		nsStatus.setValue("In");
		nsWarranty.setValue("NO");
		dfFirstCheckDate.setValue(null);
		dfInShop.setValue(null);
		dfOutShop.setValue(null);
		dfSecondCheckDate.setValue(null);
	}

}
