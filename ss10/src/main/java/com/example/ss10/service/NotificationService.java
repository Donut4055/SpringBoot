package com.example.ss10.service;


import com.example.ss10.model.entity.Account;
import com.example.ss10.model.entity.Notification;
import com.example.ss10.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final AccountService accountService;

    public void createNotification(Account account, String content) {
        Notification notification = new Notification();
        notification.setAccount(account);
        notification.setContent(content);
        notification.setStatus("chưa đọc");

        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByAccount(UUID accountId) {
        Account account = accountService.getAccountById(accountId);
        return notificationRepository.findByAccountOrderByCreatedAtDesc(account);
    }
}
