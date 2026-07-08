package com.ledger.ledger_system.service;

import com.ledger.ledger_system.entity.LedgerEntry;
import com.ledger.ledger_system.repository.LedgerEntryRepository;
import com.ledger.ledger_system.util.HashUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LedgerService {

    private final LedgerEntryRepository ledgerRepository;

    public LedgerService(LedgerEntryRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public LedgerEntry createEntry(
            Long journalId,
            Integer accountCode,
            double debit,
            double credit,
            String currency,
            String description) {

        String previousHash = getPreviousHash();

        String data = journalId +
                accountCode +
                debit +
                credit +
                currency +
                description +
                previousHash;

        String currentHash = HashUtil.generateHash(data);

        LedgerEntry entry = LedgerEntry.builder()
                .journalId(journalId)
                .accountCode(accountCode)
                .debit(java.math.BigDecimal.valueOf(debit))
                .credit(java.math.BigDecimal.valueOf(credit))
                .currency(currency)
                .description(description)
                .previousHash(previousHash)
                .currentHash(currentHash)
                .idempotencyKey(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .build();

        return ledgerRepository.save(entry);
    }

    private String getPreviousHash() {

        Optional<LedgerEntry> lastEntry =
                ledgerRepository.findTopByOrderByIdDesc();

        return lastEntry
                .map(LedgerEntry::getCurrentHash)
                .orElse("GENESIS");
    }

}