package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckConstants {
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
    public static String CHECK_PATH = "check/%s.pdf";
    public static String STATEMENT_TEMPLATE = """
            ������                     | %-30s
            ����                       | %-20s
            ������                     | BYN
            ���� ��������              | %-20s
            ������                     | %-10s - %-10s
            ���� � ����� ������������  | %-10s  %-10s
            �������                    | %-20.2f BYN
            """;
    public static String STATEMENT_PATH = "statement/%s.pdf";
    public static String MONEY_STATEMENT_PATH = "money-statement/%s.pdf";
    public static String FONT_PATH = "fonts/er-kurier-1251.ttf";
}
