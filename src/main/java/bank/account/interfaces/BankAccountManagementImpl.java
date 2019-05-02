package bank.account.interfaces;

import bank.account.constants.Messages;
import bank.account.constants.OperationType;
import bank.account.entities.AccountOperation;
import bank.account.entities.BankAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankAccountManagementImpl implements BankAccountManagement {

    public BankAccount create(String accountNumber, BigDecimal initialAmount, String currency) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setBalance(initialAmount);
        account.setCreatedDate(new Date());
        account.setCurrency(currency);
        return account;
    }

    public void deposit(BankAccount account, BigDecimal amount, String description) {
        // create deposit operation and modify the account balance
        if(account == null){
            return;
        }
        BigDecimal newAmount = account.getBalance().add(amount);
        AccountOperation depositOperation = new AccountOperation(
                account.getAccountNumber(), OperationType.DEPOSIT, new Date(), amount, account.getBalance(),null,description);
        account.getOperations().add(depositOperation);
        account.setModifiedDate(new Date());
        account.setBalance(newAmount);
    }

    public void withdrawal(BankAccount account, BigDecimal amount, String description) {
        // create withdrawal operation and modify the account balance
        if(account == null){
            return;
        }
        String comment = null;
        if(account.getBalance().compareTo(amount)<0){
            comment = Messages.INSUFFICIENT_BALANCE;
        }
        BigDecimal newAmount = account.getBalance().subtract(amount);
        AccountOperation withdrawalOperation = new AccountOperation(
                account.getAccountNumber(), OperationType.WITHDRAWAL, new Date(), amount, account.getBalance(),comment,description);
        account.getOperations().add(withdrawalOperation);
        account.setModifiedDate(new Date());
        account.setBalance(newAmount);
    }

    public List<AccountOperation> getOperationsHistory(BankAccount account, Date date) {
        if(account == null){
            return new ArrayList<AccountOperation>();
        }
        if(date == null){
           return account.getOperations();
        }
       return   account.getOperations()
               .stream()
               .filter(op->date.equals(op.getDate())).collect(Collectors.toList());
    }
}
