package com.MIU.OnlineJob.Services.Notifiers;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.MIU.OnlineJob.Models.User;

class EmailNotifier extends NotificationService {
	private final Logger LOGGER = LoggerFactory.getLogger(EmailNotifier.class.getName());

	private JavaMailSender javaMailSender;

	public EmailNotifier(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public boolean sendNotification(User user, String subject, String notification) {

		try {

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

			mimeMessageHelper.setTo(user.getEmail());
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(notification);
			javaMailSender.send(message);
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}

}
