package utils.constants;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class PatternConstants {
    public static Pattern ACC_NUMBER_PATTERN = Pattern.compile("\\w");

}
