package com.ledger.ledger_system.service;

import com.ledger.ledger_system.entity.Notification;
import com.ledger.ledger_system.events.TransactionEvent;
import com.ledger.ledger_system.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void saveNotification(TransactionEvent event) {

        Notification notification = Notification.builder()
                .referenceNumber(event.getReferenceNumber())
                .message("Transaction Successful")
                .status("SENT")
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(notification);
    }
}