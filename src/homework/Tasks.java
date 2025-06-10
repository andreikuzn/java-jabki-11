package homework;

import homework.exceptions.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class Tasks {

    private Tasks() {
        throw new AssertionError("Создание экземпляров - запрещено!");
    }

    private static final Map<String, String> ITEMS = Map.of(
            "123", "Книга",
            "456", "Тетрадь",
            "789", "Ручка"
    );

    // 1. Безопасное деление
    public static Integer safeDivide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль запрещено");
            return null;
        }
    }

    // 2. Проверка строки
    public static void validateString(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("Строка пустая или состоит только из пробелов");
        }
    }

    // 3. Преобразование строки в число
    public static List<Integer> convertStringToNum(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        for (String s : strings) {
            try {
                result.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                System.out.printf("Не удалось преобразовать строку %s в число%n", s);
            }
        }
        return result;
    }

    // 4. Простая валидация возраста
    public static String setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть меньше нуля");
        }
        return String.format("Возраст установлен: %d", age);
    }

    // 5. Собственное исключение: депозит
    public static String deposit(double amount) throws NegativeDepositException {
        if (amount <= 0) {
            throw new NegativeDepositException(amount);
        }
        return String.format("Внесено на депозит: %.2f", amount);
    }

    // 6. Поиск товара по коду
    public static String getItem(String code) {
        if (!ITEMS.containsKey(code)) {
            throw new ItemNotFoundException(code);
        }
        return ITEMS.get(code);
    }

    // 7. Чтение из файла
    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.printf("Ошибка чтения файла: %s%n", e.getMessage());
        }
        return lines;
    }

    // 8. Система логина
    public static String login(String username, String password) throws LoginFailedException {
        String correctUser = "admin";
        String correctPass = "1234";
        if (!correctUser.equals(username) || !correctPass.equals(password)) {
            throw new LoginFailedException();
        }
        return String.format("Добро пожаловать, %s!", username);
    }

    // 9. Банковский перевод с валидацией
    public static double[] transfer(double fromBalance, double toBalance, double amount)
            throws InvalidTransferAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidTransferAmountException(amount);
        }
        if (fromBalance < amount) {
            throw new InsufficientBalanceException(amount, fromBalance);
        }
        fromBalance -= amount;
        toBalance += amount;
        System.out.printf("Переведено %.2f. Новый баланс отправителя: %.2f, получателя: %.2f%n",
                amount, fromBalance, toBalance);
        return new double[]{fromBalance, toBalance};
    }

    // 10. Сервис оценки товара
    private static final List<Integer> ratings = new ArrayList<>();

    public static String rateProduct(int rating) throws InvalidRatingException {
        if (rating < 1 || rating > 5) {
            throw new InvalidRatingException(rating);
        }
        ratings.add(rating);
        return String.format("Рейтинг успешно сохранён: %d", rating);
    }

    public static String rateProduct(String ratingStr) {
        try {
            int rating = Integer.parseInt(ratingStr);
            return rateProduct(rating);
        } catch (NumberFormatException e) {
            return String.format("Рейтинг %s не является числом", ratingStr);
        } catch (InvalidRatingException e) {
            return e.getMessage();
        }
    }
}
