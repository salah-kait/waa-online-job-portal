package com.MIU.OnlineJob.Services.Notifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.MIU.OnlineJob.Factories.Enums.NotificationType;

@Service
public class NotificationServiceFactory {
	private JavaMailSender javaMailSender;
	private SystemNotificationService systemNotificationService;

	@Autowired
	public NotificationServiceFactory(JavaMailSender javaMailSender,
			SystemNotificationService systemNotificationService) {
		this.javaMailSender = javaMailSender;
		this.systemNotificationService = systemNotificationService;
	}

	public NotificationService getService(NotificationType notificationType) {
		switch (notificationType) {
		case Email:
			return new EmailNotifier(javaMailSender);
		case Sms:
			throw new UnsupportedOperationException();
			//return new SmsNotifier();
		case System:
			return new SystemNotifier(systemNotificationService);
		default:
			break;
		}
		return null;

	}
}
