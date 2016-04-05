package com.mechanicshop.components;

import javax.annotation.PostConstruct;

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
public class DataEntryLayout extends Panel{
	
	VerticalLayout mainLayout = new VerticalLayout();
	HorizontalLayout hLayoutForms = new HorizontalLayout();
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

	
	private void buildLayout(){
		FormLayout formLayout1 = new FormLayout();
		 
		formLayout1.setMargin(new MarginInfo(false, true, false, true));
		formLayout1.setSpacing(true);
		
		
		FormLayout formLayout2 = new FormLayout();
		formLayout2.setMargin(new MarginInfo(false, true, false, true));
		formLayout2.setSpacing(true);
		
		final NumberField tfTag = new NumberField("Tag");
		
		tfTag.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfTag.setWidth("500px");
		
		final TextField tfPhone = new TextField("Phone");
		
		tfPhone.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfPhone.setWidth("500px");
		final TextField tfName = new TextField("Name");
		
		tfName.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfName.setWidth("500px");
		
		final TextField tfVehicle = new TextField("Vehicle");
		tfVehicle.setWidth("500px");
		tfVehicle.setStyleName(ValoTheme.TEXTFIELD_TINY);
	
		final TextField tfLicensePlate = new TextField("LicensePlate");
		
		tfLicensePlate.setStyleName(ValoTheme.TEXTFIELD_TINY);
		tfLicensePlate.setWidth("500px");
		final TextField tfVin = new TextField("Vin");
		tfVin.setWidth("500px");
		tfVin.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final PopupDateField dfInShop = new PopupDateField("InShop");
		dfInShop.setImmediate(true);
		dfInShop.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfInShop.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
		dfInShop.setInputPrompt("Select");
		dfInShop.setWidth("500px");
		
		final PopupDateField dfOutShop = new PopupDateField("OutShop");
		dfOutShop.setImmediate(true);
		dfOutShop.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfOutShop.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
		dfOutShop.setInputPrompt("Select");
		dfOutShop.setWidth("500px");
	
		final TextField tfStatus = new TextField("Status");
		tfStatus.setWidth("500px");
		tfStatus.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final NumberField tfMileage = new NumberField("Mileage");
		tfMileage.setWidth("500px");
		tfMileage.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfPicked = new TextField("Picked");
		tfPicked.setWidth("500px");
		tfPicked.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfPayment = new TextField("Payment");
		tfPayment.setWidth("500px");
		tfPayment.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfRemarks = new TextField("Remarks");
		tfRemarks.setWidth("500px");
		tfRemarks.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfRebuilder = new TextField("Rebuilder");
		tfRebuilder.setWidth("500px");
		tfRebuilder.setStyleName(ValoTheme.TEXTFIELD_TINY);
		

		final TextField tfInstaller = new TextField("Installer");
		tfInstaller.setWidth("500px");
		tfInstaller.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfFirstCheckBy = new TextField("FirstCheckBy");
		tfFirstCheckBy.setWidth("500px");
		tfFirstCheckBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfSecondCheckBy = new TextField("SecondCheckBy");
		tfSecondCheckBy.setWidth("500px");
		tfSecondCheckBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final PopupDateField dfFirstCheckDate = new PopupDateField("FirstCheckDate");
		dfFirstCheckDate.setImmediate(true);
		dfFirstCheckDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfFirstCheckDate.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
		dfFirstCheckDate.setInputPrompt("Select");
		dfFirstCheckDate.setWidth("500px");
		
		final PopupDateField dfSecondCheckDate = new PopupDateField("SecondCheckDate");
		dfSecondCheckDate.setImmediate(true);
		dfSecondCheckDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfSecondCheckDate.setInputPrompt("Select");
		dfSecondCheckDate.setWidth("500px");
	
		
		final TextArea taMedia = new TextArea("Media");
		taMedia.setWidth("500px");
		taMedia.setStyleName(ValoTheme.TEXTFIELD_TINY);

		
		final TextArea taMedia2 = new TextArea("Media2");
		taMedia2.setWidth("500px");
		taMedia2.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
	
		final TextField tfReferedBy = new TextField("ReferedBy");
		tfReferedBy.setWidth("500px");
		tfReferedBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfWarrantyLimit = new TextField("WarrantyLimit");
		tfWarrantyLimit.setWidth("500px");
		tfWarrantyLimit.setStyleName(ValoTheme.TEXTFIELD_TINY);
		

		final NativeSelect nsWarranty = new NativeSelect("Warranty");
		nsWarranty.setWidth("500px");
		nsWarranty.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsWarranty.addItems("Yes", "No");
		
		
		final NativeSelect nsSMS = new NativeSelect("SMS");
		nsSMS.setWidth("500px");
		nsSMS.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsSMS.addItems("Yes", "No");
		
		
		final NativeSelect nsComeback = new NativeSelect("Comeback");
		nsComeback.setWidth("500px");
		nsComeback.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsComeback.addItems("Yes", "No");
		
	
		formLayout1.addComponents(tfTag, tfPhone, tfName, tfVehicle, tfLicensePlate, tfVin, dfInShop, dfOutShop,
				tfStatus, tfMileage, tfPicked, tfPayment, tfRemarks, tfRebuilder, tfInstaller, tfFirstCheckBy);
		
		formLayout2.addComponents(tfSecondCheckBy, dfFirstCheckDate, dfSecondCheckDate, taMedia, taMedia2, tfReferedBy,
				tfWarrantyLimit, nsWarranty, nsSMS, nsComeback);

		

		
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
		Button cancelBtn = new Button("Cancel");
		cancelBtn.setStyleName(ValoTheme.BUTTON_TINY);
		cancelBtn.addStyleName(ValoTheme.BUTTON_DANGER);
		cancelBtn.setImmediate(true);
//		cancelBtn.addClickListener(new ClickListener() {
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//				subWindow.close();
//
//			}
//		});

		layoutButtons.setSizeUndefined();
		layoutButtons.setSpacing(true);
		layoutButtons.addComponents(cancelBtn, sendBtn);

		hLayoutForms.addComponents(formLayout1, formLayout2);
		
		mainLayout.addComponent(hLayoutForms);
		mainLayout.addComponent(layoutButtons);
		mainLayout.setComponentAlignment(hLayoutForms, Alignment.MIDDLE_CENTER);
		mainLayout.setComponentAlignment(layoutButtons, Alignment.MIDDLE_RIGHT);
		mainLayout.setExpandRatio(hLayoutForms, 3);

	}
}
