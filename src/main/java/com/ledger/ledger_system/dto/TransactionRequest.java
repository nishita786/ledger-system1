package com.ledger.ledger_system.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @NotNull(message = "Debit account is required")
    private Integer debitAccount;

    @NotNull(message = "Credit account is required")
    private Integer creditAccount;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Transaction type is required")
    private String transactionType;
}