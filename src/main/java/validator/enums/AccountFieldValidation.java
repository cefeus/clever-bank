package validator.enums;
import dto.AccountDto;
import org.apache.logging.log4j.util.TriConsumer;
import validator.AccountValidator;

import java.util.Set;

/**
 * Класс содержащий перечисления полей по которым нужно сделать валидацию
 */
public enum AccountFieldValidation {
    NUMBER(AccountValidator::validateNumber),
    AMOUNT(AccountValidator::validateAmount);

    private final TriConsumer<AccountValidator, AccountDto, Set<String>> consumer;

    AccountFieldValidation(TriConsumer<AccountValidator, AccountDto, Set<String>> consumer){
        this.consumer = consumer;
    }

    /**
     * метод для передачи параметров консъюмеру
     * @param validator - класс где реализуются методы валидации
     * @param accDto
     * @param messages - множесто содержащее сообщения об ошибках
     */
    public void consume(AccountValidator validator, AccountDto accDto, Set<String> messages){
        consumer.accept(validator, accDto, messages);
    }

}

