package validator;


import com.mchange.v2.util.CollectionUtils;
import dto.AccountDto;
import exception.ValidationException;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import utils.PropertiesUtil;
import validator.enums.AccFieldValidation;

import java.util.*;
import java.util.regex.Pattern;

import static utils.constants.ExceptionMessagesConstants.INCORRECT_ACC_NUMBER;
import static utils.constants.PatternConstants.ACC_NUMBER_PATTERN;

public class AccountValidator {

    public void validate(AccountDto accDto) {
        List<String> exceptionMessages = new ArrayList<>();

        Arrays.stream(AccFieldValidation.values())
                .forEach(field -> field.consume(this, accDto, exceptionMessages));

        if(!exceptionMessages.isEmpty())
            throw new ValidationException(exceptionMessages);

    }

    public void validateNumber(AccountDto accDto, List<String> exceptionMessages){
        val accFrom = accDto.getAccFrom();
        val accTo = accDto.getAccTo();

        if(StringUtils.isNotBlank(accFrom) && !ACC_NUMBER_PATTERN.matcher(accFrom).matches()){
            exceptionMessages
                    .add(PropertiesUtil.getPropertyByKey(INCORRECT_ACC_NUMBER)
                            .formatted(accFrom));
        }

        if(StringUtils.isNotBlank(accTo) && !ACC_NUMBER_PATTERN.matcher(accTo).matches()){
            exceptionMessages
                    .add(PropertiesUtil.getPropertyByKey(INCORRECT_ACC_NUMBER)
                            .formatted(accTo));
        }
    }
    public void validateAmount(AccountDto accDto, List<String> exceptionMessages){


    }

}
