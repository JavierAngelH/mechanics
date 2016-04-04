package com.mechanicshop.components;

import javax.annotation.PostConstruct;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import tm.kod.widgets.numberfield.NumberField;


@SpringComponent
@UIScope
public class DataEntryLayout extends VerticalLayout{

	@PostConstruct
	void init() {
		addStyleName(ValoTheme.LAYOUT_WELL);
		setMargin(true);
		setSizeFull();
		setSpacing(true);
buildLayout();
	}

	
	private void buildLayout(){
		FormLayout formLayout = new FormLayout();
		 
		formLayout.setMargin(new MarginInfo(false, true, false, true));
		formLayout.setSpacing(true);
		formLayout.setSizeFull();
		
		final NumberField tfTag = new NumberField("Tag");
		tfTag.setRequired(true);
		tfTag.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfPhone = new TextField("Phone");
		tfPhone.setRequired(true);
		tfPhone.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfName = new TextField("Name");
		tfName.setRequired(true);
		tfName.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		
		final TextField tfVehicle = new TextField("Vehicle");
		tfVehicle.setRequired(true);
		tfVehicle.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfLicensePlate = new TextField("LicensePlate");
		tfLicensePlate.setRequired(true);
		tfLicensePlate.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfVin = new TextField("Vin");
		tfVin.setRequired(true);
		tfVin.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final PopupDateField dfInShop = new PopupDateField("InShop");
		dfInShop.setImmediate(true);
		dfInShop.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfInShop.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
		dfInShop.setInputPrompt("Select");
		dfInShop.setTextFieldEnabled(false);
		
		final PopupDateField dfOutShop = new PopupDateField("OutShop");
		dfOutShop.setImmediate(true);
		dfOutShop.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfOutShop.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
		dfOutShop.setInputPrompt("Select");
		dfOutShop.setTextFieldEnabled(false);
	
		final TextField tfStatus = new TextField("Status");
		tfStatus.setRequired(true);
		tfStatus.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final NumberField tfMileage = new NumberField("Mileage");
		tfMileage.setRequired(true);
		tfMileage.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfPicked = new TextField("Picked");
		tfPicked.setRequired(true);
		tfPicked.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfPayment = new TextField("Payment");
		tfPayment.setRequired(true);
		tfPayment.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfRemarks = new TextField("Remarks");
		tfRemarks.setRequired(true);
		tfRemarks.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
	
		  `Rebuilder` varchar(20) DEFAULT NULL,
		  `Installer` varchar(20) DEFAULT NULL,
		  `FirstCheckBy` varchar(100) DEFAULT NULL,
		  `SecondCheckBy` varchar(100) DEFAULT NULL,
		  `FirstCheckDate` date DEFAULT NULL,
		  `SecondCheckDate` date DEFAULT NULL,
		  `Media` text,
		  `Media2` text,
		  `ReferedBy` varchar(20) DEFAULT NULL,
		  `WarrantyLimit` varchar(5) DEFAULT NULL,
		  `Warranty` varchar(5) DEFAULT NULL,
		  `SMS` varchar(3) DEFAULT 'NO',
		  `Comeback` varchar(3) DEFAULT 'YES',
		
		
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

	
		addComponent(formLayout);
		addComponent(layoutButtons);
		setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
		setComponentAlignment(layoutButtons, Alignment.MIDDLE_RIGHT);
		setExpandRatio(formLayout, 3);

	}
}
