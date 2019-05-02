package bank_account_test;


import bank.account.BankAccountApplication;
import bank.account.constants.Currency;
import bank.account.constants.Messages;
import bank.account.constants.OperationType;
import bank.account.entities.AccountOperation;
import bank.account.entities.BankAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= BankAccountApplication.class)
public class ManagementAccountTest extends BankAccountCreation {

    private BankAccount account;

    private static BigDecimal initialAmount = new BigDecimal(100);
    private static String accountNumber = "BHNJ1234";

    @Before
    public void createBankAccount(){
        account = createBankAccount(accountNumber, initialAmount, Currency.EURO);
    }
    // user Stories 1
    @Test
    public void depositMoneyTest(){
        bankAccountManagement.deposit(account,new BigDecimal(200),"PRIME");
        assertEquals(account.getBalance(), (new BigDecimal(300)));
    }

    // user stories 2
    @Test
    public void withdrawalMoneyTest(){
        bankAccountManagement.withdrawal(account,new BigDecimal(50.5),"SUPERMARKET CREDIT CARD");
        assertEquals(account.getBalance(), (new BigDecimal(49.5)));

        bankAccountManagement.withdrawal(account,new BigDecimal(100),"PHARMACY CREDIT CARD");
        assertEquals(account.getBalance(), (new BigDecimal(-50.5)));
        assertEquals(account.getOperations().get(1).getComment(), Messages.INSUFFICIENT_BALANCE);
    }

    @Test
    public void getOperationsHistory(){
        // init data
        bankAccountManagement.deposit(account,new BigDecimal(300),"SALARY");
        bankAccountManagement.deposit(account,new BigDecimal(300),"PRIME");
        bankAccountManagement.withdrawal(account,new BigDecimal(100),"PHARMACY CREDIT CARD");
        //treatment
        List<AccountOperation> operations = bankAccountManagement.getOperationsHistory(account,null);
        List<AccountOperation> depositOperations = bankAccountManagement.getOperationsHistory(account,new Date())
                                                   .stream()
                                                   .filter(op-> OperationType.DEPOSIT.equals(op.getType()))
                                                   .collect(Collectors.toList());

        //result
        assertEquals(operations,3);
        assertEquals(operations,2);


    }
}
