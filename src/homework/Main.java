package homework;

import homework.exceptions.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Безопасное деление
        System.out.println("1. Безопасное деление:");
        Tasks.safeDivide(1, 0);
        Tasks.safeDivide(0, 0);
        System.out.printf("10 / 2 = %d%n%n", Tasks.safeDivide(10, 2));

        // 2. Проверка строки
        System.out.println("2. Проверка строки:");
        try {
            Tasks.validateString("    ");
        } catch (IllegalArgumentException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        try {
            Tasks.validateString("Текст");
            System.out.println("Строка корректна");
        } catch (IllegalArgumentException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        System.out.println();

        // 3. Преобразование строки в число
        System.out.println("3. Преобразование строки в число:");
        List<Integer> numbers = Tasks.convertStringToNum(List.of("155", "two", "9", "12q", "-47"));
        System.out.printf("Преобразованные числа: %s%n%n", numbers);

        // 4. Простая валидация возраста
        System.out.println("4. Простая валидация возраста:");
        Tasks.setAge(0);
        Tasks.setAge(25);
        try {
            System.out.println(Tasks.setAge(-1));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();

        // 5. Депозит с собственным исключением
        System.out.println("5. Собственное исключение: депозит");
        try {
            Tasks.deposit(-1);
        } catch (NegativeDepositException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        try {
            Tasks.deposit(0);
        } catch (NegativeDepositException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        try {
            Tasks.deposit(300);
        } catch (NegativeDepositException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        System.out.println();

        // 6. Поиск товара по коду
        System.out.println("6. Поиск товара по коду:");
        try {
            System.out.printf("Товар с кодом 123: %s%n", Tasks.getItem("123"));
            System.out.printf("Товар с кодом 999: %s%n", Tasks.getItem("999"));
        } catch (ItemNotFoundException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        System.out.println();

        // 7. Чтение из файла
        System.out.println("7. Чтение из файла:");
        List<String> lines = Tasks.readFile("file.txt");
        System.out.printf("Прочитанные строки: %s%n%n", lines);

        // 8. Система логина
        System.out.println("8. Система логина:");
        try {
            Tasks.login("admin", "pass");
        } catch (LoginFailedException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        try {
            Tasks.login("admin", "1234");
        } catch (LoginFailedException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        System.out.println();

        // 9. Банковский перевод с валидацией
        System.out.println("9. Банковский перевод:");
        double from = 100.0;
        double to = 50.0;
        try {
            double[] res = Tasks.transfer(from, to, 30.0);
            from = res[0];
            to = res[1];
            Tasks.transfer(from, to, 200.0);
        } catch (InvalidTransferAmountException | InsufficientBalanceException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        try {
            Tasks.transfer(from, to, 0.0);
        } catch (InvalidTransferAmountException | InsufficientBalanceException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        try {
            Tasks.transfer(from, to, -1.0);
        } catch (InvalidTransferAmountException | InsufficientBalanceException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
        System.out.printf("Баланс отправителя: %.2f%n", from);
        System.out.printf("Баланс получателя: %.2f%n%n", to);

        // 10. Сервис оценки товара
        System.out.println("10. Сервис оценки товара:");
        System.out.println(Tasks.rateProduct("5"));
        System.out.println(Tasks.rateProduct("abc"));
        System.out.println(Tasks.rateProduct("7"));
        System.out.println(Tasks.rateProduct("0"));
        try {
            System.out.println(Tasks.rateProduct(3));
            System.out.println(Tasks.rateProduct(6));
        } catch (InvalidRatingException e) {
            System.out.printf("Ошибка: %s%n", e.getMessage());
        }
    }
}
