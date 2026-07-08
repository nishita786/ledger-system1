package com.ledger.ledger_system.repository;

import com.ledger.ledger_system.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LedgerEntryRepository extends JpaRepository<LedgerEntry,Long> {

    Optional<LedgerEntry> findTopByOrderByIdDesc();

}