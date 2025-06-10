package homework;

import homework.exceptions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {

    @Test
    void safeDivideTest() {
        assertEquals(5, Tasks.safeDivide(10, 2));
        assertNull(Tasks.safeDivide(5, 0));
        assertNull(Tasks.safeDivide(0, 0));
    }

    @Test
    void validateStringTest() {
        assertThrows(IllegalArgumentException.class, () -> Tasks.validateString("    "));
        assertThrows(IllegalArgumentException.class, () -> Tasks.validateString(""));
        Tasks.validateString("Текст");
    }

    @Test
    void convertStringToNumTest() {
        List<Integer> result = Tasks.convertStringToNum(List.of("10", "two", "5", "-4", "12q"));
        assertEquals(List.of(10, 5, -4), result);
    }

    @Test
    void setAgeTest() {
        assertEquals("Возраст установлен: 20", Tasks.setAge(20));
        assertEquals("Возраст установлен: 0", Tasks.setAge(0));
        assertThrows(IllegalArgumentException.class, () -> Tasks.setAge(-1));
    }

    @Test
    void depositTest() throws NegativeDepositException {
        assertEquals("Внесено на депозит: 100,00", Tasks.deposit(100.0));
        assertThrows(NegativeDepositException.class, () -> Tasks.deposit(0.0));
        assertThrows(NegativeDepositException.class, () -> Tasks.deposit(-50.0));
    }

    @Test
    void getItemTest() {
        assertEquals("Книга", Tasks.getItem("123"));
        assertThrows(ItemNotFoundException.class, () -> Tasks.getItem("999"));
    }

    @Test
    void readFileTest() throws IOException {
        Path file = Files.createTempFile("testfile", ".txt");
        Files.write(file, List.of("строка1", "строка2", "строка3"));
        List<String> result = Tasks.readFile(file.toString());
        assertEquals(List.of("строка1", "строка2", "строка3"), result);
        Files.deleteIfExists(file);
        assertTrue(Tasks.readFile("testfile.txt").isEmpty());
    }

    @Test
    void loginTest() throws LoginFailedException {
        assertEquals("Добро пожаловать, admin!", Tasks.login("admin", "1234"));
        assertThrows(LoginFailedException.class, () -> Tasks.login("admin", "pass"));
    }

    @Test
    void transferTest() throws InvalidTransferAmountException, InsufficientBalanceException {
        double[] res = Tasks.transfer(100.0, 50.0, 30.0);
        assertArrayEquals(new double[]{70.0, 80.0}, res, 0.0001);
        assertThrows(InvalidTransferAmountException.class, () -> Tasks.transfer(100.0, 50.0, 0.0));
        assertThrows(InvalidTransferAmountException.class, () -> Tasks.transfer(100.0, 50.0, -1.0));
        assertThrows(InsufficientBalanceException.class, () -> Tasks.transfer(20.0, 10.0, 50.0));
    }

    @Test
    void rateProductIntTest() throws InvalidRatingException {
        assertEquals("Рейтинг успешно сохранён: 4", Tasks.rateProduct(4));
        assertThrows(InvalidRatingException.class, () -> Tasks.rateProduct(6));
        assertThrows(InvalidRatingException.class, () -> Tasks.rateProduct(0));
        assertThrows(InvalidRatingException.class, () -> Tasks.rateProduct(-1));
    }

    @Test
    void rateProductStringTest() {
        assertEquals("Рейтинг успешно сохранён: 3", Tasks.rateProduct("3"));
        assertEquals("Рейтинг 7abc не является числом", Tasks.rateProduct("7abc"));
        assertTrue(Tasks.rateProduct("0").contains("Рейтинг должен быть от 1 до 5"));
        assertTrue(Tasks.rateProduct("6").contains("Рейтинг должен быть от 1 до 5"));
    }
}