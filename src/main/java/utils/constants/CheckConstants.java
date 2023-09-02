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
    public static String CHECK_PATH = "check/%s.pdf";
    public static String STATEMENT_TEMPLATE = """
            Клиент                     | %-30s
            Счет                       | %-20s
            Валюта                     | BYN
            Дата открытия              | %-20s
            Период                     | %-10s - %-10s
            Дата и время формирования  | %-10s  %-10s
            Остаток                    | %-20.2f BYN
            """;
    public static String STATEMENT_PATH = "statement/%s.pdf";
    public static String MONEY_STATEMENT_PATH = "money-statement/%s.pdf";
    public static String FONT_PATH = "fonts/er-kurier-1251.ttf";
}
