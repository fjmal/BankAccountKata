package bank.account.enums;


public enum CurrencyEnum {
    EURO(0 , "EUR");


    public Integer value ;
    public String label;
    CurrencyEnum(int i, String desc) {
        value = i;
        label = desc;
    }
}
