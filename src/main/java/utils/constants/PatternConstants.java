package utils.constants;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@UtilityClass
public class PatternConstants {
    public static Pattern ACC_NUMBER_PATTERN = Pattern.compile("BY[0-9]{2}[a-zA-Z]{4}[0-9]{20}");
    public static DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy, hh.mm");
    public static DateTimeFormatter DATE_TIME_PATH_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

}
