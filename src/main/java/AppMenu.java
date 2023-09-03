import dto.AccountDto;
import dto.StatementDto;
import exception.IllegalParametersException;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import service.AccountService;
import service.StatementService;
import service.impl.AccountServiceImpl;
import service.impl.StatementServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

import static model.StatementType.TRANSACTIONS_HISTORY;

/**
 * класс для взаимодействия с пользователем
 */
public class AppMenu {
    private final Scanner sc = new Scanner(System.in);
    private final AccountService accService = new AccountServiceImpl();
    private final StatementService stmtService = new StatementServiceImpl();
    private String choice = Strings.EMPTY;

    /**
     * основной метод, который запускает стартовое меню
     */
    public void start() {
        while (true) {
            System.out.println("\n-------------------");
            System.out.println("Clever Bank");
            System.out.println("-------------------\n");
            System.out.println("1. Платежи");
            System.out.println("2. Получить выписку по транзакциям");
            System.out.println("3. Выход");
            System.out.print("\nEnter your choice: ");

            choice = sc.next();

            switch (choice) {
                case "1" -> accOperations();
                case "2" -> transactionStatement();
                case "3" -> {
                    return;
                }
                default -> System.out.println("Wrong input\n");
            }
        }
    }

    /**
     * метод для выбора операций со счетом
     */
    private void accOperations() {
        System.out.println("Выберите операцию:\n");
        System.out.println("1. Пополнение счета\n");
        System.out.println("2. Снятие средств\n");
        System.out.println("3. Перевод на другой счет\n");

        choice = sc.next();

        val accDto = getAccDto(choice);

        switch (choice) {
            case "1" -> accService.deposit(accDto);
            case "2" -> accService.withdraw(accDto);
            case "3" -> accService.transfer(accDto);
            default -> System.out.println("Неверный ввод");
        }
    }

    /**
     * метод для формирование выписки по транзакциям за определенный промежуток времени
     */
    private void transactionStatement() {
        System.out.println("Номер счета:\n");
        val number = sc.next();
        System.out.println("Выберите промежуток времени:\n");
        System.out.println("1. Месяц\n");
        System.out.println("2. Год\n");
        System.out.println("3. За все время\n");

        choice = sc.next();

        try {
            val statementDto = getStatementDto(choice, number);
            stmtService.createTransactionStatement(statementDto);

        } catch (IllegalParametersException e) {
            return;
        }

    }

    /**
     * метод для формирования AccountDto
     * @param choice - пользовательский выбор
     * @return AccountDto
     */
    private AccountDto getAccDto(String choice) {

        System.out.println("Номер счета:\n");
        val number = sc.next();
        System.out.println("Введите сумму:\n");
        val amount = sc.nextBigDecimal();

        var accDto = new AccountDto(number, amount);
        if (Integer.parseInt(choice) > 2) {
            System.out.println("Номер второго счета:\n");
            accDto.setAccTo(sc.next());
        }

        return accDto;
    }

    /**
     * метод для формирования StatementDto
     * @param choice - пользовательский выбор
     * @param number - номер счета
     * @return StatementDto
     */
    private StatementDto getStatementDto(String choice, String number) {
        LocalDate start;
        LocalDate end = LocalDate.now();

        switch (choice) {
            case "1" -> start = end.minusMonths(1);
            case "2" -> start = end.minusYears(1);
            case "3" -> start = LocalDate.MAX;
            default -> throw new IllegalParametersException("Вы ввели неверный параметр");
        }

        return StatementDto.builder()
                .accNumber(number)
                .dateFrom(start)
                .dateTo(end.plusDays(1))
                .type(TRANSACTIONS_HISTORY)
                .build();
    }
}
