package validator.enums;
import dto.AccountDto;
import org.apache.logging.log4j.util.TriConsumer;
import validator.AccountValidator;

import java.util.Set;

public enum AccountFieldValidation {
    NUMBER(AccountValidator::validateNumber),
    AMOUNT(AccountValidator::validateAmount);

    private final TriConsumer<AccountValidator, AccountDto, Set<String>> consumer;

    AccountFieldValidation(TriConsumer<AccountValidator, AccountDto, Set<String>> consumer){
        this.consumer = consumer;
    }

    public void consume(AccountValidator validator, AccountDto accDto, Set<String> messages){
        consumer.accept(validator, accDto, messages);
    }

}

