package ru.innopolis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.service.ResourceGetter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceGetterImpl implements ResourceGetter {
    private  Logger logger = LoggerFactory.getLogger(ResourceGetterImpl.class);

    /**
     * Проверяет является ресурс файлом либо ссылкой
     * @param path - путь к ресурсу
     * @return текст из ресурса
     */
    public String getResourceByPath(String path)  {
        String text = "";
        if (detectUrl(path)) {
            try {
                text = getResource(new URL(path));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            text = getResource(new File(path));
        }
        return text;
    }

    private String getResource(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("Input/Output exception in getResource() by File");
        }
        return sb.toString();
    }

    private String getResource(URL url) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
        } catch (IOException e) {
            logger.warn("Input/Output exception in getResource() by URL");
        }
        return sb.toString();
    }

    private boolean detectUrl(String pathResource) {
        String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pathResource);
        return m.matches();
    }

}
