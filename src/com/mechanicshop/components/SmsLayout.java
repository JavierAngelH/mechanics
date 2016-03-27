package com.mechanicshop.components;

import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.mechanicshop.service.SearchService;
import com.mechanicshop.service.SmsSenderService;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Sms;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class SmsLayout extends HorizontalLayout {
	TextField tfPhoneNumber = new TextField();
	TextArea taMessage = new TextArea();
	Label titleLabel = new Label("SEND SMS");

	Label titleLabelInbox = new Label("INBOX");
	ComboBox cboxSearch = new ComboBox();
	Window subWindow = new Window();
	Table inboxTable = new Table();
	 BeanItemContainer<Sms> smsContainer = new BeanItemContainer<Sms>(
			 Sms.class);
	@Autowired
	SmsSenderService smsSenderService;
	
	@Autowired
	SearchService searchService;
	
	@PostConstruct
    void init() {
		setSizeFull();
		addStyleName(ValoTheme.LAYOUT_WELL);
		setMargin(true);
		setSpacing(true);
		buildLayout();
		
	}
	

	private void buildLayout() {
	VerticalLayout layoutSendSMS = new VerticalLayout();
		tfPhoneNumber.setImmediate(true);
		taMessage.setImmediate(true);
		tfPhoneNumber.setRequired(true);
		taMessage.setRequired(true);
		HorizontalLayout layoutTitle = new HorizontalLayout();
		layoutTitle.setSizeUndefined();
		layoutTitle.setWidth("100%");
		layoutTitle.setSpacing(true);
		layoutTitle.setMargin(new MarginInfo(false, true, true, true));
		titleLabel.addStyleName(ValoTheme.LABEL_H2);
		titleLabel.addStyleName(ValoTheme.LABEL_COLORED);
		titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		titleLabel.addStyleName(ValoTheme.LABEL_BOLD);
		titleLabel.setSizeUndefined();
		layoutTitle.addComponent(titleLabel);
		layoutTitle.setComponentAlignment(titleLabel, Alignment.MIDDLE_CENTER);

		
		
		HorizontalLayout layoutPhoneNumber = new HorizontalLayout();
	
		layoutPhoneNumber.setSpacing(true);
		Label lbPhoneNumber = new Label("Phone Number");
		lbPhoneNumber.setSizeUndefined();
		lbPhoneNumber.setStyleName(ValoTheme.LABEL_TINY);
		tfPhoneNumber.setStyleName(ValoTheme.TEXTAREA_TINY);
		cboxSearch.setStyleName(ValoTheme.COMBOBOX_TINY);
		cboxSearch.setInputPrompt("Select Client");
		cboxSearch.setTextInputAllowed(false);
		layoutPhoneNumber.addComponent(lbPhoneNumber);
		layoutPhoneNumber.addComponent(tfPhoneNumber);
		layoutPhoneNumber.addComponent(cboxSearch);
		layoutPhoneNumber.setComponentAlignment(lbPhoneNumber, Alignment.MIDDLE_LEFT);
		layoutPhoneNumber.setComponentAlignment(tfPhoneNumber, Alignment.MIDDLE_LEFT);
		layoutPhoneNumber.setComponentAlignment(cboxSearch, Alignment.MIDDLE_RIGHT);
		layoutPhoneNumber.setSizeUndefined();
		layoutPhoneNumber.setWidth("100%");
		layoutPhoneNumber.setExpandRatio(tfPhoneNumber, 3);
		
		cboxSearch.addItems(searchService.getClients());
	cboxSearch.addValueChangeListener(cboxValueListener);
		
		taMessage.setColumns(30);
		taMessage.setRows(10);
		taMessage.addStyleName(ValoTheme.TEXTAREA_SMALL);
		VerticalLayout layoutText = new VerticalLayout();

		layoutText.addComponent(layoutPhoneNumber);

		layoutText.addComponent(taMessage);
		layoutText.setComponentAlignment(layoutPhoneNumber, Alignment.TOP_CENTER);
		layoutText.setComponentAlignment(taMessage, Alignment.TOP_CENTER);
		layoutText.setExpandRatio(taMessage, 3);
		layoutText.setSizeUndefined();

		layoutText.setSpacing(true);
		HorizontalLayout layoutButtons = new HorizontalLayout();
		layoutButtons.setMargin(false);
		layoutButtons.setSpacing(true);
		layoutButtons.setSizeUndefined();
		layoutButtons.setWidth("100%");
		Button sendSMSBtn = new Button("Send SMS");

		Button sendVoiceSBtn = new Button("Send Voice");
		sendSMSBtn.addClickListener(sendBtnListener);
		sendSMSBtn.setImmediate(true);
		sendSMSBtn.setStyleName(ValoTheme.BUTTON_TINY);
		sendSMSBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);

		sendVoiceSBtn.addClickListener(sendBtnListener);
		sendVoiceSBtn.setImmediate(true);
		sendVoiceSBtn.setStyleName(ValoTheme.BUTTON_TINY);
		sendVoiceSBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		
		layoutButtons.addComponents(sendSMSBtn);
		layoutButtons.setComponentAlignment(sendSMSBtn, Alignment.MIDDLE_RIGHT);
		layoutButtons.setExpandRatio(sendSMSBtn, 3);
	
		layoutButtons.addComponents(sendVoiceSBtn);
		layoutButtons.setComponentAlignment(sendVoiceSBtn, Alignment.MIDDLE_RIGHT);
		
	
		layoutText.addComponent(layoutButtons);
		layoutText.setComponentAlignment(layoutButtons,  Alignment.MIDDLE_CENTER);
		
		layoutText.setMargin(true);

		layoutText.setStyleName(ValoTheme.LAYOUT_CARD);
		layoutSendSMS.addComponent(layoutTitle);
		layoutSendSMS.setComponentAlignment(layoutTitle, Alignment.TOP_CENTER);
		layoutSendSMS.addComponent(layoutText);
		layoutSendSMS.setComponentAlignment(layoutText, Alignment.TOP_CENTER);
		layoutSendSMS.setExpandRatio(layoutText, 3);
		
		VerticalLayout layoutInbox = new VerticalLayout();
		layoutInbox.setSizeFull();
		HorizontalLayout layoutTitleInbox = new HorizontalLayout();
		layoutTitleInbox.setSizeUndefined();
		layoutTitleInbox.setWidth("100%");
		layoutTitleInbox.setSpacing(true);
		layoutTitleInbox.setMargin(new MarginInfo(false, true, true, true));
		titleLabelInbox.addStyleName(ValoTheme.LABEL_H2);
		titleLabelInbox.addStyleName(ValoTheme.LABEL_COLORED);
		titleLabelInbox.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		titleLabelInbox.addStyleName(ValoTheme.LABEL_BOLD);
		titleLabelInbox.setSizeUndefined();
		layoutTitleInbox.addComponent(titleLabelInbox);
		layoutTitleInbox.setComponentAlignment(titleLabelInbox, Alignment.MIDDLE_CENTER);
		layoutInbox.addComponent(layoutTitleInbox);
		layoutInbox.addComponent(inboxTable);
		inboxTable.setSortEnabled(true);
		inboxTable.setStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
		inboxTable.addStyleName(ValoTheme.TABLE_SMALL);
		inboxTable.addStyleName(ValoTheme.TABLE_COMPACT);
		inboxTable.setWidth("100%");
		layoutInbox.setExpandRatio(inboxTable, 3);
		
		addComponent(layoutSendSMS);
		addComponent(layoutInbox);
	}
	
	
public void fillInbox(){
	smsContainer.addAll(smsSenderService.getReceivedMessages());
	inboxTable.setContainerDataSource(smsContainer);
	inboxTable.setVisibleColumns("from", "body", "dateSent");
	inboxTable.setColumnHeaders("From", "Message", "Date");
	inboxTable.setColumnExpandRatio("body", 3);
	inboxTable.setPageLength(11);
}
	private ClickListener sendBtnListener = new ClickListener() {

		@Override
		public void buttonClick(ClickEvent event) {
			try{
				String option = event.getButton().getCaption();
				tfPhoneNumber.validate();
				taMessage.validate();
				String number = tfPhoneNumber.getValue();
				String message = taMessage.getValue();
				
				try {
					if(option.equalsIgnoreCase("Send SMS"))
					smsSenderService.sendMessage(message, number);
					else
					smsSenderService.sendVoiceMessage(message, number);
					Notification.show("Message sent successfully");
				} catch (TwilioRestException e) {
					Notification.show("Error sending the message", e.getMessage(), Notification.Type.ERROR_MESSAGE);
					e.printStackTrace();
				}

			}catch(EmptyValueException e){
				System.out.println("empty value");
			
			}
			
			
		}
	};

private ValueChangeListener cboxValueListener = new ValueChangeListener() {
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		String value = (String) event.getProperty().getValue();
		StringTokenizer tokenizer = new StringTokenizer(value, "--");
		tokenizer.nextElement();
		String phoneNumber = (String)tokenizer.nextElement();
		tfPhoneNumber.setValue(phoneNumber.trim());
	}
};
	
}
