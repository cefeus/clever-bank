import dto.AccountDto;
import lombok.val;
import service.AccountService;
import service.impl.AccountServiceImpl;

import java.util.Scanner;

public class AppMenu {
    private final Scanner sc = new Scanner(System.in);
    private final AccountService accService = new AccountServiceImpl();
    private int choice = -1;

    public void start() {
        while (true) {
            System.out.println("\n-------------------");
            System.out.println("Clever Bank");
            System.out.println("-------------------\n");
            System.out.println("1. Платежи");
            System.out.println("2. Панель управления");
            System.out.println("3. Exit.");
            System.out.print("\nEnter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> accOperations();
                case 2 -> System.out.println("Second case");
                default -> System.out.println("Wrong input\n");
            }
        }
    }

    private void accOperations() {
        System.out.println("Выберите операцию:\n");
        System.out.println("1. Пополнение счета\n");
        System.out.println("2. Снятие средств\n");
        System.out.println("3. Перевод на другой счет\n");

        choice = sc.nextInt();

        val accDto = getAccDto(choice);

        switch (choice) {
            case 1 -> accService.deposit(accDto);
            case 2 -> accService.withdraw(accDto);
            case 3 -> accService.transfer(accDto);
            default -> System.out.println("Неверный ввод");
        }
    }

    public AccountDto getAccDto(int choice) {

        System.out.println("Номер первого счета:\n");
        val number = sc.next();
        System.out.println("Введите сумму:\n");
        val amount = sc.nextBigDecimal();

        var accDto = new AccountDto(number, amount);
        if (choice > 2) {
            System.out.println("Номер второго счета:\n");
            accDto.setAccTo(sc.next());
        }
        return accDto;
    }
}
