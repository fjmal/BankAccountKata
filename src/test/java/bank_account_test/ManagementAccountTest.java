package bank_account_test;


import bank.account.BankAccountApplication;
import bank.account.entities.AccountOperation;
import bank.account.entities.BankAccount;
import bank.account.enums.CurrencyEnum;
import bank.account.enums.MessageEnum;
import bank.account.enums.OperationTypeEnum;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= BankAccountApplication.class)
public class ManagementAccountTest extends BankAccountCreation  {

    private BankAccount account;

    private static BigDecimal initialAmount = new BigDecimal(100);
    private static String accountNumber = DataEnum.ACCOUNT_NUMBER.value;

    @Before
    public void createBankAccount(){
        account = createBankAccount(accountNumber, initialAmount, CurrencyEnum.EURO.label);
    }
    // user Stories 1
    @Test
    public void depositMoneyTest(){
        bankAccountManagement.deposit(account,new BigDecimal(200),DataEnum.DEPOSIT_DESCRIPTION_PRIME.value);
        assertEquals(account.getBalance(), (new BigDecimal(300)));
    }

    // user stories 2
    @Test
    public void withdrawalMoneyTest(){
        bankAccountManagement.withdrawal(account,new BigDecimal(50.5),DataEnum.WITHDRAWAL_DESCRIPTION_SUPERMARKET.value);
        assertEquals(account.getBalance(), (new BigDecimal(49.5)));

        bankAccountManagement.withdrawal(account,new BigDecimal(100),DataEnum.WITHDRAWAL_DESCRIPTION_PHARMACY.value);
        assertEquals(account.getBalance(), (new BigDecimal(-50.5)));
        assertEquals(account.getOperations().get(1).getComment(), MessageEnum.INSUFFICIENT_BALANCE.label);
    }

    @Test
    public void getOperationsHistory(){
        // init data
        bankAccountManagement.deposit(account,new BigDecimal(300),DataEnum.DEPOSIT_DESCRIPTION_SALARY.value);
        bankAccountManagement.deposit(account,new BigDecimal(300),DataEnum.DEPOSIT_DESCRIPTION_PRIME.value);
        bankAccountManagement.withdrawal(account,new BigDecimal(100),DataEnum.WITHDRAWAL_DESCRIPTION_PHARMACY.value);
        //treatment
        List<AccountOperation> operations = bankAccountManagement.getOperationsHistory(account,new Date(),new Date());
        List<AccountOperation> depositOperations = operations.stream()
                                                   .filter(op-> OperationTypeEnum.DEPOSIT.equals(op.getType()))
                                                   .collect(Collectors.toList());

        //result
        assertEquals(operations.size(),3);
        assertEquals(depositOperations.size(),2);



    }
}
