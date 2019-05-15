package bank.account.impl;

import bank.account.entities.AccountOperation;
import bank.account.entities.BankAccount;
import bank.account.enums.MessageEnum;
import bank.account.enums.OperationTypeEnum;
import bank.account.interfaces.BankAccountManagement;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        BigDecimal balanceAfterOperation = account.getBalance().add(amount);
        AccountOperation depositOperation = new AccountOperation(
                account.getAccountNumber(), OperationTypeEnum.DEPOSIT, new Date(), amount, balanceAfterOperation,null,description);
        account.getOperations().add(depositOperation);
        account.setModifiedDate(new Date());
        account.setBalance(balanceAfterOperation);
    }

    public void withdrawal(BankAccount account, BigDecimal amount, String description) {
        // create withdrawal operation and modify the account balance
        if(account == null){
            return;
        }
        String comment = null;
        if(account.getBalance().compareTo(amount)<0){
            comment = MessageEnum.INSUFFICIENT_BALANCE.label;
        }
        BigDecimal balanceAfterOperation = account.getBalance().subtract(amount);
        AccountOperation withdrawalOperation = new AccountOperation(
                account.getAccountNumber(), OperationTypeEnum.WITHDRAWAL, new Date(), amount, balanceAfterOperation,comment,description);
        account.getOperations().add(withdrawalOperation);
        account.setModifiedDate(new Date());
        account.setBalance(balanceAfterOperation);
    }

    public List<AccountOperation> getOperationsHistory(BankAccount account, Date firstDate, Date secondDate) {
        if(account == null || firstDate == null || secondDate ==null ){
            return new ArrayList<AccountOperation>();
        }
        List<AccountOperation> operations = account.getOperations()
                .stream()
                .filter(op -> (op.getDate().after(firstDate) && op.getDate().before(secondDate)) ||
                        (op.getDate().equals(firstDate) || (op.getDate().equals(secondDate))))
                .collect(Collectors.toList());
        operations.stream().forEach(op->{
            System.out.println(op.toString());
        });

        return operations;
    }
}
