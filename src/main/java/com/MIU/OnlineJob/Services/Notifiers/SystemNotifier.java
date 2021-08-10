package com.MIU.OnlineJob.Services.Notifiers;

import org.springframework.beans.factory.annotation.Autowired;

import com.MIU.OnlineJob.Models.User;

public class SystemNotifier extends NotificationService {

	private SystemNotificationService systemNotificationService;

	@Autowired
	public SystemNotifier(SystemNotificationService systemNotificationService) {
		this.systemNotificationService = systemNotificationService;
	}

	@Override
	public boolean sendNotification(User user, String subject, String body) {
		return systemNotificationService.add(user, subject, body);
	}

}
