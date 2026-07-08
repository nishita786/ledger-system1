package com.ledger.ledger_system.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent implements Serializable {

    private Long journalId;

    private String referenceNumber;

    private Integer debitAccount;

    private Integer creditAccount;

    private BigDecimal amount;

    private String currency;

    private String transactionType;

    private String description;

    private LocalDateTime timestamp;
}