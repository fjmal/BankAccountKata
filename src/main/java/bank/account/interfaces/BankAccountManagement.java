package bank.account.interfaces;

import bank.account.entities.AccountOperation;
import bank.account.entities.BankAccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BankAccountManagement {

        BankAccount create(String accountNumber, BigDecimal initialAmount, String currency);
        void deposit(BankAccount account, BigDecimal amount, String description);
        void withdrawal(BankAccount account, BigDecimal amount, String description);
        List<AccountOperation> getOperationsHistory(BankAccount account, Date firstDate,Date secondDate);



}
