package homework.exceptions;

public class InvalidTransferAmountException extends Exception {
    public InvalidTransferAmountException(double amount) {
        super(String.format("Сумма перевода должна быть больше нуля. Попытка перевода: %.2f", amount));
    }
}
