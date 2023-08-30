package validator.enums;
import dto.AccountDto;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.TriConsumer;
import utils.PropertiesUtil;
import validator.AccountValidator;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import static utils.constants.ExceptionMessagesConstants.INCORRECT_ACC_NUMBER;
import static utils.constants.PatternConstants.ACC_NUMBER_PATTERN;

public enum AccFieldValidation {
    NUMBER(AccountValidator::validateNumber),
    AMOUNT(AccountValidator::validateAmount);

    private final TriConsumer<AccountValidator, AccountDto, Set<String>> consumer;

    AccFieldValidation(TriConsumer<AccountValidator, AccountDto, Set<String>> consumer){
        this.consumer = consumer;
    }

    public void consume(AccountValidator validator, AccountDto accDto, Set<String> messages){
        consumer.accept(validator, accDto, messages);
    }

}

