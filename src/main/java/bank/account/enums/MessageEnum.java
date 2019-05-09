package bank.account.enums;

public enum MessageEnum {
    INSUFFICIENT_BALANCE(0 , "Insufficient balance with this operation");


    public Integer value ;
    public String label;
    MessageEnum(int i, String desc) {
        value = i;
        label = desc;
    }
}
