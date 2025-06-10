package homework.exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(double amount, double fromBalance) {
        super(String.format("Недостаточно средств для перевода. Попытка перевода: %.2f с баланса: %.2f", amount, fromBalance));
    }
}