package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckConstants {
    public static final String CURRENT_DIR = System.getProperty("user.dir");
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
    public static String CHECK_PATH = CURRENT_DIR + "/check/%s.txt";
    public static String TRANSFER_FROM = "ﾏ褞裘�� �� ";
    public static String TRANSFER_TO = "ﾏ褞裘�� ";
    public static String TRANSACTION_STATEMENT_TEMPLATE_HEADER = """
                                      ﾂ��頌��
                                       %-20s
            ﾊ�韃��                     | %-30s
            ﾑ�褪                       | %-20s
            ﾂ琿���                     | BYN
            ﾄ瑣� ��������              | %-20s
            ﾏ褞韶�                     | %-10s - %-10s
            ﾄ瑣� � 糅褌� ����頏�籵���  | %-10s 
            ﾎ��瑣��                    | %-10.2f BYN
            """;
    public static String TRANSACTION_HISTORY_STATEMENT_TEMPLATE_BODY = """
                 ﾄ瑣�      |          ﾏ�韲褶瑙�                     |     ﾊ��顆褥�粽     
            -----------------------------------------------------------------------------
            """;
    public static String MONEY_FLOW_STATEMENT_TEMPLATE_BODY = """
                  ﾏ�頷��      |      ﾓ���      
            -------------------------------------
            """;
    public static String TRANSACTION_STATEMENT_TEMPLATE_FOOTER = "%15s|%40s|%15S";
    public static String MONEY_FLOW_STATEMENT_TEMPLATE_FOOTER = "%15s BYN| %15s BYN";
    public static String STATEMENT_PATH = "YOUR_ABSOLUTE_PATH/%s.txt";  //Insert your absolute path here
}
