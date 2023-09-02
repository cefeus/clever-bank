package utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckConstants {
    public static String CHECK_TEMPLATE = """
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
    public static String STATEMENT_PATH = "statement/%s_%s.pdf";
    public static String MONEY_STATEMENT_PATH = "";
    public static String FONT_PATH = "fonts/courier-new.ttf";
}
