package com.ledger.ledger_system.service;

import com.ledger.ledger_system.dto.TransactionRequest;
import com.ledger.ledger_system.dto.TransactionResponse;
import com.ledger.ledger_system.entity.JournalEntry;
import com.ledger.ledger_system.events.TransactionEvent;
import com.ledger.ledger_system.publisher.TransactionPublisher;
import com.ledger.ledger_system.validator.TransactionValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionValidator validator;
    private final AccountService accountService;
    private final JournalService journalService;
    private final LedgerService ledgerService;
    private final TransactionPublisher transactionPublisher;

    public TransactionService(
            TransactionValidator validator,
            AccountService accountService,
            JournalService journalService,
            LedgerService ledgerService,
            TransactionPublisher transactionPublisher) {

        this.validator = validator;
        this.accountService = accountService;
        this.journalService = journalService;
        this.ledgerService = ledgerService;
        this.transactionPublisher = transactionPublisher;
    }

    @Transactional
    public TransactionResponse postTransaction(TransactionRequest request) {

        // Validate request
        validator.validate(request);

        // Verify accounts exist
        accountService.getAccount(request.getDebitAccount());
        accountService.getAccount(request.getCreditAccount());

        // Create journal entry
        JournalEntry journal = journalService.createJournal(
                request.getTransactionType(),
                request.getDescription()
        );

        // Debit entry
        ledgerService.createEntry(
                journal.getId(),
                request.getDebitAccount(),
                request.getAmount().doubleValue(),
                0.0,
                request.getCurrency(),
                request.getDescription()
        );

        // Credit entry
        ledgerService.createEntry(
                journal.getId(),
                request.getCreditAccount(),
                0.0,
                request.getAmount().doubleValue(),
                request.getCurrency(),
                request.getDescription()
        );

        // Publish RabbitMQ Event
        TransactionEvent event = TransactionEvent.builder()
                .journalId(journal.getId())
                .referenceNumber(journal.getReferenceNumber())
                .debitAccount(request.getDebitAccount())
                .creditAccount(request.getCreditAccount())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .transactionType(request.getTransactionType())
                .description(request.getDescription())
                .timestamp(LocalDateTime.now())
                .build();

        transactionPublisher.publish(event);

        // Return API response
        return TransactionResponse.builder()
                .journalId(journal.getId())
                .referenceNumber(journal.getReferenceNumber())
                .status("SUCCESS")
                .message("Transaction Posted Successfully")
                .timestamp(LocalDateTime.now())
                .build();
    }
}