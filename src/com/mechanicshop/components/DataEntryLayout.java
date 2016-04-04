package com.mechanicshop.components;

import javax.annotation.PostConstruct;

import tm.kod.widgets.numberfield.NumberField;

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
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


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
		
		final TextField tfRebuilder = new TextField("Rebuilder");
		tfRebuilder.setRequired(true);
		tfRebuilder.setStyleName(ValoTheme.TEXTFIELD_TINY);
		

		final TextField tfInstaller = new TextField("Installer");
		tfInstaller.setRequired(true);
		tfInstaller.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfFirstCheckBy = new TextField("FirstCheckBy");
		tfFirstCheckBy.setRequired(true);
		tfFirstCheckBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfSecondCheckBy = new TextField("SecondCheckBy");
		tfSecondCheckBy.setRequired(true);
		tfSecondCheckBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final PopupDateField dfFirstCheckDate = new PopupDateField("FirstCheckDate");
		dfFirstCheckDate.setImmediate(true);
		dfFirstCheckDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfFirstCheckDate.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
		dfFirstCheckDate.setInputPrompt("Select");
		dfFirstCheckDate.setTextFieldEnabled(false);
		
		final PopupDateField dfSecondCheckDate = new PopupDateField("SecondCheckDate");
		dfSecondCheckDate.setImmediate(true);
		dfSecondCheckDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfSecondCheckDate.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
		dfSecondCheckDate.setInputPrompt("Select");
		dfSecondCheckDate.setTextFieldEnabled(false);
	
		
		final TextArea taMedia = new TextArea("Media");
		taMedia.setRequired(true);
		taMedia.setStyleName(ValoTheme.TEXTFIELD_TINY);

		
		final TextArea taMedia2 = new TextArea("Media2");
		taMedia2.setRequired(true);
		taMedia2.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
	
		final TextField tfReferedBy = new TextField("ReferedBy");
		tfReferedBy.setRequired(true);
		tfReferedBy.setStyleName(ValoTheme.TEXTFIELD_TINY);
		
		final TextField tfWarrantyLimit = new TextField("WarrantyLimit");
		tfWarrantyLimit.setRequired(true);
		tfWarrantyLimit.setStyleName(ValoTheme.TEXTFIELD_TINY);
		

		final NativeSelect nsWarranty = new NativeSelect("Warranty");
		nsWarranty.setRequired(true);
		nsWarranty.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsWarranty.addItems("Yes", "No");
		
		
		final NativeSelect nsSMS = new NativeSelect("SMS");
		nsSMS.setRequired(true);
		nsSMS.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsSMS.addItems("Yes", "No");
		
		
		final NativeSelect nsComeback = new NativeSelect("Comeback");
		nsComeback.setRequired(true);
		nsComeback.setStyleName(ValoTheme.TEXTFIELD_TINY);
		nsComeback.addItems("Yes", "No");
		
	
		formLayout.addComponents(tfTag, tfPhone, tfName, tfVehicle, tfLicensePlate, tfVin, dfInShop, dfOutShop,
				tfStatus, tfMileage, tfPicked, tfPayment, tfRemarks, tfRebuilder, tfInstaller, tfFirstCheckBy,
				tfSecondCheckBy, dfFirstCheckDate, dfSecondCheckDate, taMedia, taMedia2, tfReferedBy,
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

	
		addComponent(formLayout);
		addComponent(layoutButtons);
		setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
		setComponentAlignment(layoutButtons, Alignment.MIDDLE_RIGHT);
		setExpandRatio(formLayout, 3);

	}
}
