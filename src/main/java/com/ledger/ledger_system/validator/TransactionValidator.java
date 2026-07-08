package com.ledger.ledger_system.validator;

import com.ledger.ledger_system.dto.TransactionRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionValidator {

    public void validate(TransactionRequest request) {

        if (request.getDebitAccount().equals(request.getCreditAccount())) {
            throw new IllegalArgumentException(
                    "Debit account and Credit account cannot be the same.");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Transaction amount must be greater than zero.");
        }

        if (request.getCurrency() == null || request.getCurrency().isBlank()) {
            throw new IllegalArgumentException(
                    "Currency is required.");
        }

        if (request.getTransactionType() == null || request.getTransactionType().isBlank()) {
            throw new IllegalArgumentException(
                    "Transaction type is required.");
        }
    }
}