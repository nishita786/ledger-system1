package com.ledger.ledger_system.repository;

import com.ledger.ledger_system.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}