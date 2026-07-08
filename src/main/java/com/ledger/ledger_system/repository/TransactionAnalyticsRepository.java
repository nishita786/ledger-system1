package com.ledger.ledger_system.repository;

import com.ledger.ledger_system.entity.TransactionAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionAnalyticsRepository
        extends JpaRepository<TransactionAnalytics, Long> {
}