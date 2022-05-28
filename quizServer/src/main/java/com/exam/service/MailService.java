package com.exam.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.exam.helper.ExamException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Async
public class MailService {

	@Autowired
	private  JavaMailSender mailSender;
	
		
	public void sendMail(String toEmail, String body, String subject) throws ExamException{
		log.debug("Activation Mail Created...");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(toEmail);
        msg.setText(body);
        msg.setSubject(subject);
        msg.setFrom("pathakji530@gmail.com");
        
		try {
			mailSender.send(msg);
			log.info("Activation Mail Sent");
		}
		catch(MailException e) {
			log.error("Activation Mail Sent Fail");
			throw new ExamException("Exeption Occured when sending token to mail");
		}
		
	}
	
	
	

	
}
