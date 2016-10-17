package ru.innopolis.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.service.impl.ResourceGetterImpl;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Li on 11.10.16.
 */
public class ResourceGetterImplTest {

    private static Logger log = LoggerFactory.getLogger(ResourceGetterImpl.class);
    private ResourceGetterImpl resourceGetterImpl;

    @Before
    public void before() {
        this.resourceGetterImpl = new ResourceGetterImpl();
    }

    @Test
    public void testGetResourceByPath() throws IOException {
        log.info("This is testGetResourceByPath method");
        assertEquals("text not equal or bad path", this.resourceGetterImpl.getResourceByPath("TextForTest.txt"), "Товарищи!");
    }
}
