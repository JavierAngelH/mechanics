package com.mechanicshop.jobs;



import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mechanicshop.service.SmsSenderService;


@Component("sendSMSTasklet")
public class SendSMSTasklet implements Tasklet {
		
	
	@Autowired
	SmsSenderService smsSender;
				

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
			 {

		smsSender.sendMessagesMassive();
		smsSender.sendReminderMessagesMassive();
		
		return RepeatStatus.FINISHED;
	}

}
