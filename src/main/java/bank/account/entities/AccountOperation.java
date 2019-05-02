package bank.account.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AccountOperation {

    private String accountNumber;
    // 0 is a Deposit operation and 1 is a Withdrawal operation
    private Integer type;
    private Date date;
    private BigDecimal amount;
    private BigDecimal initialBalance;
    private String comment;
    private String description;

}
