package bank_account_test;

public enum DataEnum {
    ACCOUNT_NUMBER("BHNJ1234"),
    DEPOSIT_DESCRIPTION_PRIME("PRIME"),
    DEPOSIT_DESCRIPTION_SALARY("SALARY"),
    WITHDRAWAL_DESCRIPTION_SUPERMARKET("SUPERMARKET CREDIT CARD"),
    WITHDRAWAL_DESCRIPTION_PHARMACY("PHARMACY CREDIT CARD");

    public String value;
    DataEnum(String desc) {
        value = desc;
    }
}
