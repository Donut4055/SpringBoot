package com.example.ss10.repository;


import com.example.ss10.model.entity.Account;
import com.example.ss10.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findByAccountOrderByCreatedAtDesc(Account account);
}
