package homework.exceptions;

public class LoginFailedException extends Exception {
    public LoginFailedException() {
        super("Логин или пароль неверны");
    }
}