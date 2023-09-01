package utils.constants;

import lombok.experimental.UtilityClass;
import utils.PropertiesUtil;

@UtilityClass
public class SqlQueryConstants {
    public static String SQL_UPDATE_ACC_BALANCE = "UPDATE \"Accounts\" SET balance = ? WHERE \"Accounts\".number = ?";
    public static String SQL_SAVE_TRANSACTION = "INSERT INTO \"Transactions\"(acc_from, acc_to, operation_type, amount, created_at) VALUES (?,?,?,?,?)";
    public static String SQL_GET_ACC_BY_NUMBER = "SELECT * FROM \"Accounts\" WHERE \"Accounts\".number = ?";
    public static String SQL_ACCRUE_PERCENT = String.format("Update \"Accounts\" SET balance = balance + balance * %s, accrual_date = CURRENT_DATE WHERE accrual_date < NOW() - INTERVAL '30 days'", PropertiesUtil.getPropertyByKey("ACCRUE_PERSENT"));
}
