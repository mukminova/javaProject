package ru.innopolis.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.KillThreadStatus;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Поток, отслеживает потоки ReadResourceThread
 * выводит мап в режиме реального времени
 */
public class ShowThread extends Thread {
    private Map<String, Integer> uniqueWordsMap;
    private List<ReadResourceThread> threads;
    private KillThreadStatus isAlive;

    private static Logger logger = LoggerFactory.getLogger(ShowThread.class);

    public ShowThread(Map<String, Integer> uniqueWordsMap, List<ReadResourceThread> threads, KillThreadStatus isAlive) {
        this.uniqueWordsMap = uniqueWordsMap;
        this.threads = threads;
        this.isAlive = isAlive;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                //проверяем работу всех потоков, если хоть один "убит", останавливаем все
                if (!isAlive.getIsAlive()) {
                    for (ReadResourceThread thread : threads) {
                        thread.interrupt();
                    }
                } else {
                    //выводим общее количество вхождений каждого слова в консоль в режиме реального времени
                    Map.Entry entry;
                    Iterator iterator = uniqueWordsMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        entry = (Map.Entry) iterator.next();
                        System.out.println("Слово \"" + entry.getKey() + "\" встречается " + entry.getValue() + " раз");
                    }
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
    }
}

