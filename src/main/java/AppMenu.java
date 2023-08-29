import java.util.Scanner;

public class AppMenu {
    private final Scanner sc = new Scanner(System.in);
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

        switch (choice) {
            case 1 -> //TODO accService.deposit(accDto);
            case 2 -> //TODO accService.withdraw(accDto);
            case 3 -> //TODO accService.transfer(accDto);
            default -> System.out.println("Неверный ввод");
        }
    }
}
