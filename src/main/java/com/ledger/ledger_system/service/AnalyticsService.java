package com.ledger.ledger_system.service;

import com.ledger.ledger_system.entity.TransactionAnalytics;
import com.ledger.ledger_system.events.TransactionEvent;
import com.ledger.ledger_system.repository.TransactionAnalyticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalyticsService {

    private final TransactionAnalyticsRepository repository;

    public AnalyticsService(TransactionAnalyticsRepository repository) {
        this.repository = repository;
    }

    public void saveAnalytics(TransactionEvent event) {

        TransactionAnalytics analytics = TransactionAnalytics.builder()
                .referenceNumber(event.getReferenceNumber())
                .journalId(event.getJournalId())
                .debitAccount(event.getDebitAccount())
                .creditAccount(event.getCreditAccount())
                .amount(event.getAmount())
                .currency(event.getCurrency())
                .transactionType(event.getTransactionType())
                .processedAt(LocalDateTime.now())
                .build();

        repository.save(analytics);
    }
}