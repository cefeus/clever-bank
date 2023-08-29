package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlQueryConstants {

    public static String SQL_UPDATE_ACC_DEPOSIT = "UPDATE \"Accounts\" SET balance = (balance + ?) WHERE number = ?";
    public static String SQL_GET_BALANCE_BY_NUMBER = "SELECT \"Accounts\".balance FROM \"Accounts\" WHERE \"Accounts\".number = ?";
    public static String SQL_UPDATE_ACC_WITHDRAW = "UPDATE \"Accounts\" SET balance = (balance - ?) WHERE number = ?";
}
