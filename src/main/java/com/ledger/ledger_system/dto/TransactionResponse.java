package com.ledger.ledger_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Long journalId;

    private String referenceNumber;

    private String status;

    private String message;

    private LocalDateTime timestamp;
}