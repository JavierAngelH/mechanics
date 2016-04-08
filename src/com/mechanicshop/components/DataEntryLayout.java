package com.mechanicshop.components;

import java.util.Date;

import javax.annotation.PostConstruct;

import com.vaadin.data.Item;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import tm.kod.widgets.numberfield.NumberField;

@SpringComponent
@UIScope
public class DataEntryLayout extends Panel {

	VerticalLayout mainLayout = new VerticalLayout();
	HorizontalLayout hLayoutForms = new HorizontalLayout();

	final NumberField tfTag = new NumberField("Tag");
	final TextField tfPhone = new TextField("Phone");
	final TextField tfName = new TextField("Name");
	final TextField tfVehicle = new TextField("Vehicle");
	final TextField tfLicensePlate = new TextField("LicensePlate");
	final TextField tfVin = new TextField("Vin");
	final PopupDateField dfInShop = new PopupDateField("InShop");
	final PopupDateField dfOutShop = new PopupDateField("OutShop");
	final NativeSelect nsStatus = new NativeSelect("Status");
	final NumberField tfMileage = new NumberField("Mileage");
	final TextField tfPicked = new TextField("Picked");
	final TextField tfPayment = new TextField("Payment");
	final TextArea taRemarks = new TextArea("Remarks");
	final TextField tfRebuilder = new TextField("Rebuilder");
	final TextField tfInstaller = new TextField("Installer");
	final TextField tfFirstCheckBy = new TextField("FirstCheckBy");
	final TextField tfSecondCheckBy = new TextField("SecondCheckBy");
	final PopupDateField dfFirstCheckDate = new PopupDateField("FirstCheckDate");
	final PopupDateField dfSecondCheckDate = new PopupDateField(
			"SecondCheckDate");
	final TextArea taMedia = new TextArea("Media");
	final TextArea taMedia2 = new TextArea("Media2");
	final TextField tfReferedBy = new TextField("ReferedBy");
	final TextField tfWarrantyLimit = new TextField("WarrantyLimit");
	final NativeSelect nsWarranty = new NativeSelect("Warranty");
	final NativeSelect nsSMS = new NativeSelect("SMS");
	final NativeSelect nsComeback = new NativeSelect("Comeback");

	@PostConstruct
	void init() {
		addStyleName(ValoTheme.LAYOUT_WELL);
		mainLayout.setMargin(true);
		setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setSizeUndefined();
		buildLayout();
		setContent(mainLayout);
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
		tfTag.setWidth("200px");

		tfPhone.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfPhone.setWidth("200px");

		tfName.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfName.setWidth("200px");

		tfVehicle.setWidth("200px");
		tfVehicle.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfLicensePlate.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfLicensePlate.setWidth("200px");

		tfVin.setWidth("200px");
		tfVin.setStyleName(ValoTheme.TEXTFIELD_TINY);

		dfInShop.setImmediate(true);
		dfInShop.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfInShop.setInputPrompt("Select");
		dfInShop.setWidth("200px");

		dfOutShop.setImmediate(true);
		dfOutShop.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfOutShop.setInputPrompt("Select");
		dfOutShop.setWidth("200px");

		nsStatus.addItems("In", "Out", "Pending", "Ready", "Comeback");
		nsStatus.setImmediate(true);
		nsStatus.setWidth("200px");
		nsStatus.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfMileage.setWidth("200px");
		tfMileage.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfPicked.setWidth("200px");
		tfPicked.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfPayment.setWidth("200px");
		tfPayment.setStyleName(ValoTheme.TEXTFIELD_TINY);

		taRemarks.setWidth("500px");
		taRemarks.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfRebuilder.setWidth("200px");
		tfRebuilder.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfInstaller.setWidth("200px");
		tfInstaller.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfFirstCheckBy.setWidth("500px");
		tfFirstCheckBy.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfSecondCheckBy.setWidth("500px");
		tfSecondCheckBy.setStyleName(ValoTheme.TEXTFIELD_TINY);

		dfFirstCheckDate.setImmediate(true);
		dfFirstCheckDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfFirstCheckDate.setInputPrompt("Select");
		dfFirstCheckDate.setWidth("500px");

		dfSecondCheckDate.setImmediate(true);
		dfSecondCheckDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfSecondCheckDate.setInputPrompt("Select");
		dfSecondCheckDate.setWidth("500px");

		taMedia.setWidth("500px");
		taMedia.setStyleName(ValoTheme.TEXTFIELD_TINY);

		taMedia2.setWidth("500px");
		taMedia2.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfReferedBy.setWidth("200px");
		tfReferedBy.setStyleName(ValoTheme.TEXTFIELD_TINY);

		tfWarrantyLimit.setWidth("200px");
		tfWarrantyLimit.setStyleName(ValoTheme.TEXTFIELD_TINY);

		nsWarranty.setWidth("200px");
		nsWarranty.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsWarranty.addItems("Yes", "No");

		nsSMS.setWidth("200px");
		nsSMS.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsSMS.addItems("Yes", "No");

		nsComeback.setWidth("200px");
		nsComeback.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsComeback.addItems("Yes", "No");

		formLayout1.addComponents(tfTag, tfPhone, tfName, tfVehicle,
				tfLicensePlate, tfVin, dfInShop, dfOutShop, nsStatus);

		formLayout2.addComponents(tfReferedBy, tfWarrantyLimit, nsWarranty,
				nsSMS, nsComeback, tfPayment, tfRebuilder, tfInstaller,
				tfMileage, tfPicked);

		formLayout3.addComponents(tfFirstCheckBy, tfSecondCheckBy,
				dfFirstCheckDate, dfSecondCheckDate, taRemarks, taMedia,
				taMedia2);

		HorizontalLayout layoutButtons = new HorizontalLayout();
		layoutButtons.setMargin(false);
		Button sendBtn = new Button("Create");
		sendBtn.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

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
		mainLayout.setComponentAlignment(layoutButtons, Alignment.MIDDLE_RIGHT);
		mainLayout.setExpandRatio(hLayoutForms, 3);

	}

	public void fillDataEntry(Item item) {
		tfFirstCheckBy
				.setValue(item.getItemProperty("FirstCheckBy").toString());
		tfInstaller.setValue(item.getItemProperty("Installer").toString());
		taMedia.setValue(item.getItemProperty("Media").toString());
		taMedia2.setValue(item.getItemProperty("Media2").toString());
		taRemarks.setValue(item.getItemProperty("Remarks").toString());
		tfLicensePlate
				.setValue(item.getItemProperty("LicensePlate").toString());
		tfMileage.setValue(item.getItemProperty("Mileage").toString());
		tfName.setValue(item.getItemProperty("Name").toString());
		tfPayment.setValue(item.getItemProperty("Payment").toString());
		tfPhone.setValue(item.getItemProperty("Phone").toString());
		tfPicked.setValue(item.getItemProperty("Picked").toString());
		tfRebuilder.setValue(item.getItemProperty("Rebuilder").toString());
		tfReferedBy.setValue(item.getItemProperty("ReferedBy").toString());
		tfSecondCheckBy.setValue(item.getItemProperty("SecondCheckBy")
				.toString());
		tfTag.setValue(item.getItemProperty("Tag").toString());
		tfVehicle.setValue(item.getItemProperty("Vehicle").toString());
		tfVin.setValue(item.getItemProperty("Vin").toString());
		tfWarrantyLimit.setValue(item.getItemProperty("WarrantyLimit")
				.toString());
		nsComeback.setValue(item.getItemProperty("Comeback").toString());
		nsSMS.setValue(item.getItemProperty("SMS").toString());
		nsStatus.setValue(item.getItemProperty("Status").toString());
		nsWarranty.setValue(item.getItemProperty("Warranty").toString());
		dfFirstCheckDate.setValue((Date) item.getItemProperty("FirstCheckDate")
				.getValue());
		dfInShop.setValue((Date) item.getItemProperty("InShop").getValue());
		dfOutShop.setValue((Date) item.getItemProperty("OutShop").getValue());
		dfSecondCheckDate.setValue((Date) item.getItemProperty(
				"SecondCheckDate").getValue());
	}

}
