package homework.exceptions;

public class InvalidRatingException extends Exception {
    public InvalidRatingException(int rating) {
        super(String.format("Рейтинг должен быть от 1 до 5. Попытка выставления рейтинга: %d", rating));
    }
}