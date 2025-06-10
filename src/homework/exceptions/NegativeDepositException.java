package homework.exceptions;

public class NegativeDepositException extends Exception {
    public NegativeDepositException(double amount) {
        super(String.format("Депозит должен быть больше нуля. Попытка внесения: %.2f", amount));
    }
}
