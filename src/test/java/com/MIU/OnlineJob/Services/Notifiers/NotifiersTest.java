package com.MIU.OnlineJob.Services.Notifiers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;

import com.MIU.OnlineJob.DataAccess.UserRepository;
import com.MIU.OnlineJob.Factories.Enums.NotificationType;
import com.MIU.OnlineJob.Models.SystemNotification;
import com.MIU.OnlineJob.Models.User;
import com.MIU.OnlineJob.Services.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class NotifiersTest {

	@Mock
	private JavaMailSender javaMailSender;

	@Mock
	private SystemNotificationService systemNotificationService;

	@Mock
	private MimeMessage message;

	@BeforeAll
	public void setUp() throws IOException {
		// MockitoAnnotations.openMocks(this);
	}

	@Test
	public void sendNotificationByMailTest() throws MessagingException, IOException {
		NotificationServiceFactory notificationFactoryService = new NotificationServiceFactory(javaMailSender,
				systemNotificationService);
		User user = new User();
		user.setEmail("iabulubad@miu.edu");
		String subject = "Test Subject";
		String body = "Test content";
		when(javaMailSender.createMimeMessage()).thenReturn(message);

		NotificationService notificationService = notificationFactoryService.getService(NotificationType.Email);
		doNothing().when(javaMailSender).send(message);
		assertTrue(notificationService.sendNotification(user, subject, body));
		assertTrue(notificationService.sendNotification(user, body));
		SystemNotification notif = new SystemNotification();
		notif.setSubject(subject);
		notif.setBody(body);
		notif.setUser(user);
		assertTrue(notificationService.sendNotification(notif));
	}

	@Test
	public void sendNotificationThrowSystemTest() throws MessagingException, IOException {
		NotificationServiceFactory notificationFactoryService = new NotificationServiceFactory(javaMailSender,
				systemNotificationService);

		User user = new User();
		user.setEmail("iabulubad@miu.edu");
		user.setId((long) 1);
		String subject = "Test Subject";
		String body = "Test content";
		when(systemNotificationService.add(user, subject, body)).thenReturn(true);
		when(systemNotificationService.add(user, subject, body)).thenReturn(true);
		NotificationService notificationService = notificationFactoryService.getService(NotificationType.System);

		when(notificationService.sendNotification(user, body)).thenReturn(true);
		assertTrue(notificationService.sendNotification(user, subject, body));
		assertTrue(notificationService.sendNotification(user, body));
		SystemNotification notif = new SystemNotification();
		notif.setSubject(subject);
		notif.setBody(body);
		notif.setUser(user);
		when(notificationService.sendNotification(notif)).thenReturn(true);
		assertTrue(notificationService.sendNotification(notif));
	}

	@Configuration
	@Import(UserService.class)
	static class TestConfig {
		@Bean
		SystemNotificationService systemNotificationService() {
			return mock(SystemNotificationService.class);
		}

		@Bean
		UserRepository userRepository() {
			return mock(UserRepository.class);
		}
	}
}
