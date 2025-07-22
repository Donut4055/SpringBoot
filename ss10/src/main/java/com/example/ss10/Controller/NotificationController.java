package com.example.ss10.Controller;


import com.example.ss10.model.entity.Notification;
import com.example.ss10.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Notification>> getNotificationsByAccount(@PathVariable UUID accountId) {
        List<Notification> notifications = notificationService.getNotificationsByAccount(accountId);
        return ResponseEntity.ok(notifications);
    }
}

