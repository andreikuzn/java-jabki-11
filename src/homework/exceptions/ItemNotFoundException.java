package homework.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String code) {
        super(String.format("Товар с кодом %s не найден", code));
    }
}
