package com.MIU.OnlineJob.Services.Notifiers;

import com.MIU.OnlineJob.Models.SystemNotification;
import com.MIU.OnlineJob.Models.User;
import com.MIU.OnlineJob.System.PropertiesHelper;

 public abstract class NotificationService {
	public abstract boolean sendNotification(User user, String subject, String body);

	public boolean sendNotification(User user, String body) {
		return sendNotification(user,
				PropertiesHelper.getInstance().properties.getProperty("jobfinder.notifiers.emailTitle"), body);
	}

	public boolean sendNotification( SystemNotification notification) {
		return sendNotification(notification.getUser(), notification.getSubject(), notification.getBody());
	}
}
