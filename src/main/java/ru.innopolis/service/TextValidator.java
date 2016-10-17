package ru.innopolis.service;

import ru.innopolis.exception.NotCyrillicException;

public interface TextValidator {
    boolean isCyrillic(String text) throws NotCyrillicException;
}
