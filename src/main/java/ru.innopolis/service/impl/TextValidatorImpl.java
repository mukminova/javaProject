package ru.innopolis.service.impl;

import ru.innopolis.exception.NotCyrillicException;
import ru.innopolis.service.TextValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextValidatorImpl implements TextValidator {
    /**
     * Текст не должен содержать инностранных символов, только кириллица, знаки препинания и цифры
     * @param text - текст из ресурса
     * @return true - если текст удовлетворяет условию
     * @throws NotCyrillicException
     */
    public boolean isCyrillic(String text) throws NotCyrillicException {
        Pattern p = Pattern.compile("[а-яА-ЯёЁ\\d\\s\\p{Punct}]*");
        Matcher m = p.matcher(text);
        return m.matches();
    }


}
