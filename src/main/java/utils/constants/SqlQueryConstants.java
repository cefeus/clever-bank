package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlQueryConstants {
    public static String SQL_UPDATE_ACC_BALANCE = "UPDATE clever_bank.accounts SET balance = ? WHERE number = ?";
    public static String SQL_SAVE_TRANSACTION = "INSERT INTO clever_bank.transactions(acc_from, acc_to, operation_type, amount, created_at) VALUES (?,?,?,?,?)";
    public static String SQL_GET_ACC_BY_NUMBER = "SELECT * FROM clever_bank.accounts WHERE number = ?";
    public static String SQL_GET_STATEMENT_INFO_BY_NUMBER = """
            SELECT clever_bank.users.owner, clever_bank.accounts.creation_date, clever_bank.accounts.balance, clever_bank.banks.name FROM clever_bank.accounts
            INNER JOIN clever_bank.users  ON clever_bank.accounts.owner_id = clever_bank.users.id
            INNER JOIN clever_bank.banks  ON clever_bank.accounts.bank_id = clever_bank.banks.id
            WHERE clever_bank.accounts.number = ?
            """;
    public static String SQL_GET_TRANSANSACTIONS_BY_ACC = "SELECT DISTINCT * FROM transactions WHERE acc_from = ? OR acc_to = ?";

    public static String SQL_GET_BANK_NAME_BY_ACC_NUMBER = "SELECT \"Banks\".name FROM \"Accounts\" INNER JOIN \"Banks\" ON \"Accounts\".bank_id = \"Banks\".bank_id WHERE \"Accounts\".number = ?";
}
