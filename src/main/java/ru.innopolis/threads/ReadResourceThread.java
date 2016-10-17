package ru.innopolis.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.KillThreadStatus;
import ru.innopolis.exception.NotCyrillicException;
import ru.innopolis.service.impl.ResourceGetterImpl;
import ru.innopolis.service.impl.TextCounterImpl;
import ru.innopolis.service.impl.TextValidatorImpl;
import ru.innopolis.util.WordCountUtil;

import java.net.MalformedURLException;
import java.util.Map;


public class ReadResourceThread extends Thread {
    private String path;
    private Map<String, Integer> uniqueWordsMap;
    private KillThreadStatus isAlive;//переменная для остановки всех потоков
    private static Logger logger = LoggerFactory.getLogger(ReadResourceThread.class);

    public ReadResourceThread(String path, Map<String, Integer> uniqueWordsMap, KillThreadStatus isAlive) {
        this.path = path;
        this.uniqueWordsMap = uniqueWordsMap;
        this.isAlive = isAlive;
    }

    @Override
    public void run() {
        try {
            //создаем экземпляр класса для работы с ресурсом
            WordCountUtil wordCountUtil = new WordCountUtil(new ResourceGetterImpl(), new TextValidatorImpl(), new TextCounterImpl(this.uniqueWordsMap));

            String text = wordCountUtil.getResourceGetterImpl().getResourceByPath(this.path);
            //проверяем текст на пустоту
            if (!text.equals("")) {
                //проверка на валидацию
                if (!wordCountUtil.getTextValidator().isCyrillic(text)) {
                    isAlive.setIsAlive(false);
                    throw new NotCyrillicException("Incorrect symbols in path: " + this.path);
                } else if (!isInterrupted()) {
                    wordCountUtil.getTextCounter().setCountUniqueWord(text);
                }
            } else {
                logger.warn("Empty in path " + this.path);
            }
        } catch (NotCyrillicException e) {
            logger.warn(e.getMessage());
            System.err.println(e.getMessage());
        } catch (MalformedURLException e) {
            logger.warn(e.getMessage());
            System.err.println(e.getMessage());
        }
    }

}


