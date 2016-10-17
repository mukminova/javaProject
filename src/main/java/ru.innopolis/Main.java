package ru.innopolis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.threads.ReadResourceThread;
import ru.innopolis.threads.ShowThread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Map<String, Integer> uniqueWordsMap = new ConcurrentHashMap<>();
        List<ReadResourceThread> threads = new ArrayList<>();
        KillThreadStatus isAlive = new KillThreadStatus(true);
        if (args.length != 0) {
            //создаем потоки для работы с ресурсом
            for (int i = 0; i < args.length; i++) {
                ReadResourceThread thread = new ReadResourceThread(args[i], uniqueWordsMap, isAlive);
                threads.add(thread);
                thread.start();
            }
            //создаем поток-демон, который выводит общее количество вхождений каждого слова
            ShowThread showThread = new ShowThread(uniqueWordsMap, threads, isAlive);
            showThread.start();
        } else {
            logger.info("Resource path is not found");
        }
    }
}
