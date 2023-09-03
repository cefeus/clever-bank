package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckConstants {
    public static String CHECK_TEMPLATE_ENG = """
            覧覧覧覧覧覧覧覧覧覧覧覧覧覧慾
            |                                Bank check                            |
            |Check:%44s|
            |%-24s%40tT|
            |Transaction type:%59s |
            |Senders bank:%63s |
            |Receivers bank:%60s |
            |Senders account:%35s |
            |Receivers account:%32s | 
            |Amount:%66.2f BYN |
            |覧覧覧覧覧覧覧覧覧覧覧覧覧|  
            """;
    public static String CHECK_TEMPLATE_RU = """
            覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧
            |                 ﾁ瑙��糂�韜 �裲                 |
            |ﾗ裲:%44s|
            |%-24s%24tT|
            |ﾒ韵 ��瑙鈞��韋:%33s|
            |ﾁ瑙� ����珞頸褄�:%31s|
            |ﾁ瑙� �����瑣褄�:%32s|
            |ﾑ�褪 ����珞頸褄�:%31s|
            |ﾑ�褪 �����瑣褄�:%32s|
            |ﾑ����:%38.2f BYN|
            |覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧覧|  
            """;
    public static String CHECK_PATH = "check/%s.pdf";
    public static String TRANSACTION_STATEMENT_TEMPLATE = """
            ﾊ�韃��                     | %-30s
            ﾑ�褪                       | %-20s
            ﾂ琿���                     | BYN
            ﾄ瑣� ��������              | %-20s
            ﾏ褞韶�                     | %-10s - %-10s
            ﾄ瑣� � 糅褌� ����頏�籵���  | %-10s  %-10s
            ﾎ��瑣��                    | %-20.2f BYN
            """;
    public static String STATEMENT_PATH = "statement/%s.pdf";
    public static String MONEY_STATEMENT_PATH = "money-statement/%s.pdf";
    public static String FONT_PATH = "fonts/er-kurier-1251.ttf";
}
