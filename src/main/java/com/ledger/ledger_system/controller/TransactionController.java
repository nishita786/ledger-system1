package com.ledger.ledger_system.controller;

import com.ledger.ledger_system.dto.TransactionRequest;
import com.ledger.ledger_system.dto.TransactionResponse;
import com.ledger.ledger_system.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/post")
    public ResponseEntity<TransactionResponse> postTransaction(
            @Valid @RequestBody TransactionRequest request) {

        TransactionResponse response =
                transactionService.postTransaction(request);

        return ResponseEntity.ok(response);
    }
}