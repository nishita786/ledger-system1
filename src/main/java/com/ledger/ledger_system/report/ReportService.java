package com.ledger.ledger_system.report;

import com.ledger.ledger_system.entity.LedgerEntry;
import com.ledger.ledger_system.repository.LedgerEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final LedgerEntryRepository ledgerRepository;

    public ReportService(LedgerEntryRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public List<LedgerEntry> getAllTransactions() {
        return ledgerRepository.findAll();
    }

}