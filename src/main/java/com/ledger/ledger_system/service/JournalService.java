package com.ledger.ledger_system.service;

import com.ledger.ledger_system.entity.JournalEntry;
import com.ledger.ledger_system.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class JournalService {

    private final JournalEntryRepository journalRepository;

    public JournalService(JournalEntryRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public JournalEntry createJournal(String transactionType,
                                      String description) {

        JournalEntry journal = JournalEntry.builder()
                .transactionType(transactionType)
                .referenceNumber(generateReference())
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();

        return journalRepository.save(journal);
    }

    private String generateReference() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}