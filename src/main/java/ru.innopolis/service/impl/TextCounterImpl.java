package ru.innopolis.service.impl;

import ru.innopolis.service.TextCounter;

import java.util.Map;

public class TextCounterImpl implements TextCounter {
    private Map<String, Integer> uniqueWordsMap;

    public TextCounterImpl(Map<String, Integer> uniqueWordsMap) {
        this.uniqueWordsMap = uniqueWordsMap;
    }

    public Map<String, Integer> getUniqueWordsMap() {
        return uniqueWordsMap;
    }

    public void setUniqueWordsMap(Map<String, Integer> uniqueWordsMap) {
        this.uniqueWordsMap = uniqueWordsMap;
    }

    /**
     * Работает с мапом, считает общее количество вхождений каждого слова
     *
     * @param text - текст из ресурса
     */
    public void setCountUniqueWord(String text) {
        String[] words = text.split("[\\p{Punct}\\s\\d]+");
        int wordCount;
        for (String word : words) {
            if (uniqueWordsMap.get(word) != null) {
                wordCount = uniqueWordsMap.get(word);
                uniqueWordsMap.put(word, ++wordCount);
            } else {
                uniqueWordsMap.put(word, 1);
            }
        }
    }
}
