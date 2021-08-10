package com.MIU.OnlineJob.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MIU.OnlineJob.Models.SystemNotification;

@Repository
public interface SystemNotificationRepository extends JpaRepository<SystemNotification, Integer> {

}
