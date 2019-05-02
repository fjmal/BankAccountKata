package bank.account.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BankAccount {

    private String accountNumber;
    private BigDecimal balance;
    private Date createdDate;
    private Date modifiedDate;
    private String currency;
    private List<AccountOperation> operations = new ArrayList<AccountOperation>();

}
