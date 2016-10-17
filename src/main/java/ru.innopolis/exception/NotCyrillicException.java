package ru.innopolis.exception;

/**
 * Ошибка валидации(проверки на кириллицу)
 */
public class NotCyrillicException extends RuntimeException {
    public NotCyrillicException(String message) {
        super(message);
    }
}
