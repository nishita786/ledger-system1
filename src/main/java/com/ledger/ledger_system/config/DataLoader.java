package com.ledger.ledger_system.config;

import com.ledger.ledger_system.entity.Account;
import com.ledger.ledger_system.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(AccountRepository repository) {
        return args -> {

            if (repository.count() == 0) {

                repository.save(Account.builder().accountCode(1001).accountName("Customer Wallet INR").accountType("ASSET").subType("CURRENT_ASSET").currency("INR").build());
                repository.save(Account.builder().accountCode(1002).accountName("Customer Wallet USD").accountType("ASSET").subType("CURRENT_ASSET").currency("USD").build());
                repository.save(Account.builder().accountCode(1003).accountName("Bank Settlement Account").accountType("ASSET").subType("CURRENT_ASSET").currency("INR").build());
                repository.save(Account.builder().accountCode(1004).accountName("FX Holding Account").accountType("ASSET").subType("CURRENT_ASSET").currency("MULTI").build());
                repository.save(Account.builder().accountCode(1005).accountName("Loan Receivable").accountType("ASSET").subType("LOAN_ASSET").currency("INR").build());

                repository.save(Account.builder().accountCode(2001).accountName("Customer Deposits").accountType("LIABILITY").subType("CURRENT_LIABILITY").currency("INR").build());
                repository.save(Account.builder().accountCode(2002).accountName("Merchant Payables").accountType("LIABILITY").subType("CURRENT_LIABILITY").currency("INR").build());
                repository.save(Account.builder().accountCode(2003).accountName("Tax Payable").accountType("LIABILITY").subType("CURRENT_LIABILITY").currency("INR").build());
                repository.save(Account.builder().accountCode(2004).accountName("Reward Points Liability").accountType("LIABILITY").subType("CURRENT_LIABILITY").currency("INR").build());
                repository.save(Account.builder().accountCode(2005).accountName("Interest Payable").accountType("LIABILITY").subType("CURRENT_LIABILITY").currency("INR").build());

                repository.save(Account.builder().accountCode(3001).accountName("Owner Equity").accountType("EQUITY").subType("CAPITAL").currency("INR").build());
                repository.save(Account.builder().accountCode(3002).accountName("Retained Earnings").accountType("EQUITY").subType("EQUITY").currency("INR").build());

                repository.save(Account.builder().accountCode(4001).accountName("Transaction Fee Revenue").accountType("REVENUE").subType("OPERATING_REVENUE").currency("INR").build());
                repository.save(Account.builder().accountCode(4002).accountName("FX Revenue").accountType("REVENUE").subType("OPERATING_REVENUE").currency("INR").build());
                repository.save(Account.builder().accountCode(4003).accountName("Interest Revenue").accountType("REVENUE").subType("OPERATING_REVENUE").currency("INR").build());
                repository.save(Account.builder().accountCode(4004).accountName("Penalty Revenue").accountType("REVENUE").subType("OPERATING_REVENUE").currency("INR").build());

                repository.save(Account.builder().accountCode(5001).accountName("Operating Expenses").accountType("EXPENSE").subType("OPERATING_EXPENSE").currency("INR").build());
                repository.save(Account.builder().accountCode(5002).accountName("Bank Charges").accountType("EXPENSE").subType("OPERATING_EXPENSE").currency("INR").build());
                repository.save(Account.builder().accountCode(5003).accountName("Marketing Expense").accountType("EXPENSE").subType("OPERATING_EXPENSE").currency("INR").build());
                repository.save(Account.builder().accountCode(5004).accountName("Interest Expense").accountType("EXPENSE").subType("OPERATING_EXPENSE").currency("INR").build());

                System.out.println("Chart of Accounts seeded successfully!");
            }
        };
    }
}