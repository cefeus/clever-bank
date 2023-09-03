package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckConstants {
    public static String CHECK_TEMPLATE_ENG = """
            —————————————————————————————|
            |                                Bank check                            |
            |Check:%44s|
            |%-24s%40tT|
            |Transaction type:%59s |
            |Senders bank:%63s |
            |Receivers bank:%60s |
            |Senders account:%35s |
            |Receivers account:%32s | 
            |Amount:%66.2f BYN |
            |——————————————————————————|  
            """;
    public static String CHECK_TEMPLATE_RU = """
            ——————————————————————————————————————————————————
            |                 Банковский чек                 |
            |Чек:%44s|
            |%-24s%24tT|
            |Тип транзакции:%33s|
            |Банк отправителя:%31s|
            |Банк получателя:%32s|
            |Счет отправителя:%31s|
            |Счет получателя:%32s|
            |Сумма:%38.2f BYN|
            |————————————————————————————————————————————————|  
            """;
    public static String CHECK_PATH = System.getProperty("user.dir") + "/check/%s.txt";
    public static String TRANSFER_FROM = "Перевод от ";
    public static String TRANSFER_TO = "Перевод ";
    public static String TRANSACTION_STATEMENT_TEMPLATE_HEADER = """
                                      Выписка
                                       %-20s
            Клиент                     | %-30s
            Счет                       | %-20s
            Валюта                     | BYN
            Дата открытия              | %-20s
            Период                     | %-10s - %-10s
            Дата и время формирования  | %-10s 
            Остаток                    | %-10.2f BYN
            """;
    public static String TRANSACTION_STATEMENT_TEMPLATE_BODY = """
                 Дата      |          Примечани                     |     Количество     
            -----------------------------------------------------------------------------
            """;
    public static String TRANSACTION_STATEMENT_TEMPLATE_FOOTER = "%15s|%40s|%15S";
    public static String STATEMENT_PATH = System.getProperty("user.dir") + "/statement/%s.txt";
    public static String MONEY_STATEMENT_PATH = "money-statement/%s.txt";
}
