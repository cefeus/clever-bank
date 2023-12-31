package utils.constants;

import lombok.experimental.UtilityClass;
/**
 * ��������� ����� ��� ����
 */
@UtilityClass
public class CheckConstants {
    public static final String CURRENT_DIR = System.getProperty("user.dir");
    public static String CHECK_TEMPLATE_ENG = """
            �����������������������������|
            |                                Bank check                            |
            |Check:%44s|
            |%-24s%40tT|
            |Transaction type:%59s |
            |Senders bank:%63s |
            |Receivers bank:%60s |
            |Senders account:%35s |
            |Receivers account:%32s | 
            |Amount:%66.2f BYN |
            |��������������������������|  
            """;
    public static String CHECK_TEMPLATE_RU = """
            ��������������������������������������������������
            |                 ���������� ���                 |
            |���:%44s|
            |%-24s%24tT|
            |��� ����������:%33s|
            |���� �����������:%31s|
            |���� ����������:%32s|
            |���� �����������:%31s|
            |���� ����������:%32s|
            |�����:%38.2f BYN|
            |������������������������������������������������|  
            """;
    public static String CHECK_PATH = CURRENT_DIR + "/check/%s.txt";
    public static String TRANSFER_FROM = "������� �� ";
    public static String TRANSFER_TO = "������� ";
    public static String TRANSACTION_STATEMENT_TEMPLATE_HEADER = """
                                      �������
                                       %-20s
            ������                     | %-30s
            ����                       | %-20s
            ������                     | BYN
            ���� ��������              | %-20s
            ������                     | %-10s - %-10s
            ���� � ����� ������������  | %-10s 
            �������                    | %-10.2f BYN
            """;
    public static String TRANSACTION_HISTORY_STATEMENT_TEMPLATE_BODY = """
                 ����      |          ���������                     |     ����������     
            -----------------------------------------------------------------------------
            """;
    public static String MONEY_FLOW_STATEMENT_TEMPLATE_BODY = """
                  ������      |      ����      
            -------------------------------------
            """;
    public static String TRANSACTION_STATEMENT_TEMPLATE_FOOTER = "%15s|%40s|%15S";
    public static String MONEY_FLOW_STATEMENT_TEMPLATE_FOOTER = "%15s BYN| %15s BYN";
    public static String STATEMENT_PATH = "YOUR_ABSOLUTE_PATH/%s.txt";  //Insert your absolute path here
}
