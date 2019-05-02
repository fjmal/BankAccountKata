package bank_account_test;

import bank.account.entities.BankAccount;
import bank.account.interfaces.BankAccountManagement;
import org.springframework.beans.factory.annotation.*;

import java.math.BigDecimal;

public class BankAccountCreation {
    @Autowired
    protected BankAccountManagement bankAccountManagement;

    protected BankAccount createBankAccount(String accountNumber, BigDecimal initialAmount, String currency){
        return bankAccountManagement.create(accountNumber,initialAmount,currency);
    }
}
