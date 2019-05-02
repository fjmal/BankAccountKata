package bank.account.enums;


public enum OperationTypeEnum {
    DEPOSIT(0),
    WITHDRAWAL (1);

    public Integer value ;
    OperationTypeEnum(int i) {
        value = i;
    }
}