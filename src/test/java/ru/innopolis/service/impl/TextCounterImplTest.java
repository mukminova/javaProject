package ru.innopolis.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.exception.NotCyrillicException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * Created by Li on 11.10.16.
 */
public class TextCounterImplTest {

    private static Logger log = LoggerFactory.getLogger(TextCounterImpl.class);
    private TextCounterImpl textCounter;

    @Before
    public void before() {
        Map<String, Integer> uniqueWordsMap = new ConcurrentHashMap<>();
        this.textCounter = new TextCounterImpl(uniqueWordsMap);
    }

    @Test
    public void testSetCountUniqueWord() {
        log.info("This is testSetCountUniqueWord method");
        try {
            this.textCounter.setCountUniqueWord("До сих пор встречается код код");
        } catch (NotCyrillicException e) {
            fail("NotCyrillicException");
            log.info("Fail NotCyrillicException in testSetCountUniqueWord method");
        }
        int mapLength = this.textCounter.getUniqueWordsMap().values().size();
        assertEquals("Fail in testSetCountUniqueWord check mapLength", mapLength, 5);
        assertEquals("Fail in testSetCountUniqueWord check count unique words", (long) this.textCounter.getUniqueWordsMap().get("встречается"), (long) 1);
        assertEquals("Fail in testSetCountUniqueWord check count unique words", (long) this.textCounter.getUniqueWordsMap().get("код"), (long) 2);
    }
}
