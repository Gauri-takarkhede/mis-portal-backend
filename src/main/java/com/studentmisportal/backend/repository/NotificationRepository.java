package com.studentmisportal.backend.repository;

import com.studentmisportal.backend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
