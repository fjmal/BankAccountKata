package bank.account.entities;

import bank.account.enums.OperationTypeEnum;
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
    private Enum<OperationTypeEnum> type;
    private Date date;
    private BigDecimal amount;
    private BigDecimal balance;
    private String comment;
    private String description;

}
