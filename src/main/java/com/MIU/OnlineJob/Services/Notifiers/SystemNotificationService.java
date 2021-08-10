package com.MIU.OnlineJob.Services.Notifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MIU.OnlineJob.DataAccess.SystemNotificationRepository;
import com.MIU.OnlineJob.Models.SystemNotification;
import com.MIU.OnlineJob.Models.User;

@Service
public class SystemNotificationService {
	private SystemNotificationRepository systemNotificationRepository;

	@Autowired
	public SystemNotificationService(SystemNotificationRepository systemNotificationRepository) {
		this.systemNotificationRepository = systemNotificationRepository;
	}

	public boolean add(User user, String subject, String body) {
		SystemNotification notifi = new SystemNotification();
		notifi.setUser(user);
		notifi.setSubject(subject);
		notifi.setBody(body);
		systemNotificationRepository.save(notifi);
		return notifi.getId() > 0;
	}
}
