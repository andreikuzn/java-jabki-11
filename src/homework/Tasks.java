package homework;

import homework.exceptions.*;

import java.io.*;
import java.util.*;


public class Tasks {
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
                System.out.printf("Не удалось преобразовать строку %s в число\n", s);
            }
        }
        return result;
    }

    // 4. Простая валидация возраста
    public static void setAge(int age) {
        try {
            if (age < 1) {
                throw new IllegalArgumentException("Возраст не может быть меньше единицы");
            }
            System.out.printf("Возраст установлен: %s\n", age);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 5. Собственное исключение: депозит
    public static void deposit(double amount) throws NegativeDepositException {
        if (amount <= 0) {
            throw new NegativeDepositException(String.format("Депозит должен быть больше нуля. Попытка внесения %s", amount));
        }
        System.out.printf("Внесено на депозит: %.2f\n", amount);
    }

    // 6. Поиск товара по коду
    private static final Map<String, String> ITEMS = Map.of(
            "123", "Книга",
            "456", "Тетрадь",
            "789", "Ручка"
    );
    public static String getItem(String code) {
        if (!ITEMS.containsKey(code)) {
            throw new ItemNotFoundException(String.format("Товар с кодом %s не найден\n", code));
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
            System.out.printf("Ошибка чтения файла: %s\n", e.getMessage());
        }
        return lines;
    }

    // 8. Система логина
    public static void login(String username, String password) throws LoginFailedException {
        String correctUser = "admin";
        String correctPass = "1234";
        if (!correctUser.equals(username) || !correctPass.equals(password)) {
            throw new LoginFailedException("Логин или пароль неверны");
        }
        System.out.printf("Добро пожаловать, %s!\n", username);
    }

    // 9. Банковский перевод с валидацией
    public static double[] transfer(double fromBalance, double toBalance, double amount)
            throws InvalidTransferAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidTransferAmountException(
                    String.format("Сумма перевода должна быть больше нуля. Попытка перевода %s", amount)
            );
        }
        if (fromBalance < amount) {
            throw new InsufficientBalanceException(
                    String.format("Недостаточно средств для перевода. Попытка перевода %s с баланса %s", amount, fromBalance)
            );
        }
        fromBalance -= amount;
        toBalance += amount;
        System.out.printf("Переведено %.2f. Новый баланс отправителя: %.2f, получателя: %.2f%n",
                amount, fromBalance, toBalance);
        return new double[]{fromBalance, toBalance};
    }

    // 10. Сервис оценки товара
    private static final List<Integer> ratings = new ArrayList<>();

    public static void rateProduct(int rating) throws InvalidRatingException {
        if (rating < 1 || rating > 5) {
            throw new InvalidRatingException(
                    String.format("Рейтинг должен быть от 1 до 5. Попытка выставления рейтинга %s", rating)
            );
        }
        ratings.add(rating);
        System.out.printf("Рейтинг успешно сохранён: %s\n", rating);
    }

    public static void rateProduct(String ratingStr) {
        try {
            int rating = Integer.parseInt(ratingStr);
            rateProduct(rating);
        } catch (NumberFormatException e) {
            System.out.printf("Рейтинг %s не является числом\n", ratingStr);
        } catch (InvalidRatingException e) {
            System.out.println(e.getMessage());
        }
    }
}
