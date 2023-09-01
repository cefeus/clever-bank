package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckConstants {

    public static String CHEQUE_TEMPLATE = """
            ——————————————————————————————————————————————————
            |                 Банковский чек                 |
            |Чек:%44d|
            |%-24s%24tT|
            |Тип транзакции:%33s|
            |Банк отправителя:%31s|
            |Банк получателя:%32s|
            |Счет отправителя:%31s|
            |Счет получателя:%32s|
            |Сумма:%38.2f BYN|
            |————————————————————————————————————————————————|  
            """;
    public static String CHECK_PATH = "";
    public static String STATEMENT_TEMPLATE = """
            Клиент                     | %-30s
            Счет                       | %-20s
            Валюта                     | BYN
            Дата открытия              | %-20s
            Период                     | %-10s - %-10s
            Дата и время формирования  | %-10s  %-10s
            Остаток                    | %-20.2f BYN
            """;
    public static String STATEMENT_PATH = "D:\\IntelliJProjects\\clever-bank\\statement\\check%s.pdf";
    public static String MONEY_STATEMENT_PATH = "";
    public static String FONT_PATH = "src/main/resources/fonts/er-kurier-1251.ttf";
}
