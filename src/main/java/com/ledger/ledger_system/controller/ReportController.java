package com.ledger.ledger_system.controller;

import com.ledger.ledger_system.entity.LedgerEntry;
import com.ledger.ledger_system.report.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/transactions")
    public List<LedgerEntry> getTransactions() {
        return reportService.getAllTransactions();
    }

}