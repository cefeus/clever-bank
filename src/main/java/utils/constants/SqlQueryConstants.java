package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlQueryConstants {
    public static String SQL_UPDATE_ACC_BALANCE = "UPDATE clever_bank.accounts SET balance = ? WHERE number = ?";
    public static String SQL_SAVE_TRANSACTION = "INSERT INTO clever_bank.transactions(id, acc_from, acc_to, transaction_type, amount, created_at) VALUES (?,?,?,?,?,?)";
    public static String SQL_GET_ACC_BY_NUMBER = "SELECT * FROM clever_bank.accounts WHERE number = ?";
    public static String SQL_GET_USER_BY_ID = "SELECT * FROM clever_bank.users WHERE id = ?";
    public static String SQL_GET_BANK_BY_ID = "SELECT * FROM clever_bank.banks WHERE id = ?";
    public static String SQL_GET_BANK_BY_ACC_NUMBER = "SELECT * FROM clever_bank.accounts INNER JOIN clever_bank.banks ON clever_bank.accounts.bank_id = clever_bank.banks.id WHERE clever_bank.accounts.number = ?";
    public static String SQL_GET_ALL_TRANSACTION = """
            Select * from clever_bank.transactions 
            WHERE (clever_bank.transactions.acc_from = ? OR clever_bank.transactions.acc_to = ?)
            AND DATE(clever_bank.transactions.created_at) BETWEEN ? AND ?
            """;
    public static String SQL_GET_ALL_TRANSFER_TRANSACTIONS_FROM = """
            SELECT clever_bank.transactions.id, clever_bank.users.name FROM clever_bank.transactions\s
                        Inner join clever_bank.accounts ON clever_bank.transactions.acc_from = clever_bank.accounts.number\s
                        Inner join clever_bank.users ON clever_bank.accounts.owner_id = clever_bank.users.id\s
                        WHERE clever_bank.transactions.transaction_type = 'TRANSFER' and clever_bank.transactions.acc_from = ?
            """;
    public static String SQL_GET_ALL_TRANSFER_TRANSACTIONS_TO = """
            SELECT clever_bank.transactions.id, clever_bank.users.name FROM clever_bank.transactions\s
                        Inner join clever_bank.accounts ON clever_bank.transactions.acc_from = clever_bank.accounts.number\s
                        Inner join clever_bank.users ON clever_bank.accounts.owner_id = clever_bank.users.id\s
                        WHERE clever_bank.transactions.transaction_type = 'TRANSFER' and clever_bank.transactions.acc_to= ?
                        """;

    public static String SQL_GET_INCOME = """
            SELECT SUM(clever_bank.transactions.amount) as UHOD from clever_bank.transactions WHERE clever_bank.transactions.acc_from = ? 
            AND (clever_bank.transactions.transaction_type = 'WITHDRAW' or clever_bank.transactions.transaction_type = 'TRANSFER') AND clever_bank.transactions.created_at BETWEEN DATE(?) AND  DATE(?) 
            """;
    public static String SQL_GET_OUTCOME = """
            SELECT SUM(clever_bank.transactions.amount) as PRIHOD from clever_bank.transactions WHERE clever_bank.transactions.acc_to = ? 
            AND (clever_bank.transactions.transaction_type = 'DEPOSIT' or clever_bank.transactions.transaction_type = 'TRANSFER') AND clever_bank.transactions.created_at BETWEEN DATE(?) AND  DATE(?)
            """;
}
