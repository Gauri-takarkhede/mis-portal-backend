package com.studentmisportal.backend.service;

import com.studentmisportal.backend.entity.Notification;
import com.studentmisportal.backend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications(){
       return notificationRepository.findAll();
    }

    public List<Notification> getLatestNotifications(){
        return notificationRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .limit(10)
                .toList();
    }
}
