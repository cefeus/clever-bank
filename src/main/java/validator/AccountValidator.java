package validator;

import dto.AccountDto;
import exception.ValidationException;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import utils.PropertiesUtil;
import validator.enums.AccountFieldValidation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static utils.constants.ExceptionMessagesConstants.INCORRECT_ACC_AMOUNT;
import static utils.constants.ExceptionMessagesConstants.INCORRECT_ACC_NUMBER;
import static utils.constants.PatternConstants.ACC_NUMBER_PATTERN;

public class AccountValidator {

    public void validate(AccountDto accDto) {
        Set<String> exceptionMessages = new HashSet<>();

        Arrays.stream(AccountFieldValidation.values())
                .forEach(field -> field.consume(this, accDto, exceptionMessages));

        if (!exceptionMessages.isEmpty())
            throw new ValidationException(exceptionMessages);

    }

    public void validateNumber(AccountDto accDto, Set<String> exceptionMessages) {
        val accFrom = accDto.getAccFrom();
        val accTo = accDto.getAccTo();

        if (StringUtils.isNotBlank(accFrom) && !ACC_NUMBER_PATTERN.matcher(accFrom).matches()) {
            exceptionMessages
                    .add(PropertiesUtil.getPropertyByKey(INCORRECT_ACC_NUMBER)
                            .formatted(accFrom));
        }

        if (StringUtils.isNotBlank(accTo) && !ACC_NUMBER_PATTERN.matcher(accTo).matches()) {
            exceptionMessages
                    .add(PropertiesUtil.getPropertyByKey(INCORRECT_ACC_NUMBER)
                            .formatted(accTo));
        }
    }

    public void validateAmount(AccountDto accDto, Set<String> exceptionMessages) {
        val amount = accDto.getAmount();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            exceptionMessages
                    .add(PropertiesUtil.getPropertyByKey(INCORRECT_ACC_AMOUNT)
                            .formatted(amount));
        }
    }

}
