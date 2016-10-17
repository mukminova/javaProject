package ru.innopolis.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.exception.NotCyrillicException;
import ru.innopolis.service.impl.TextValidatorImpl;

import static org.junit.Assert.*;

/**
 * Created by Li on 11.10.16.
 */
public class TextValidatorImplTest {

    private static Logger log = LoggerFactory.getLogger(TextValidatorImpl.class);
    private TextValidatorImpl textValidatorImpl;

    @Before
    public void before() {
        this.textValidatorImpl = new TextValidatorImpl();
    }

    @Test
    public void testGetCountUniqueWord() {
        log.info("This is checkCyrillic method");
        try {
            assertTrue(this.textValidatorImpl.isCyrillic("Проверка корректности метода"));
        } catch (NotCyrillicException e) {
            log.warn("checkCyrillic method is not valid");
            fail("NotCyrillicException");
        }
    }

}
