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
public class ManagementAccountTest  {
    
     @Autowired
    protected BankAccountManagement bankAccountManagement;

    private BankAccount account;
    

    private static BigDecimal initialAmount = new BigDecimal(100);
    private static String accountNumber = "BHNJ1234";

    @Before
    public void createBankAccount(){
        account = bankAccountManagement.create(accountNumber, initialAmount, CurrencyEnum.EURO.label);
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
        assertEquals(account.getOperations().get(1).getComment(), MessageEnum.INSUFFICIENT_BALANCE.label);
    }

    @Test
    public void getOperationsHistory(){
        // init data
        bankAccountManagement.deposit(account,new BigDecimal(300),"SALARY");
        bankAccountManagement.deposit(account,new BigDecimal(300),"PRIME");
        bankAccountManagement.withdrawal(account,new BigDecimal(100),"PHARMACY CREDIT CARD");
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
