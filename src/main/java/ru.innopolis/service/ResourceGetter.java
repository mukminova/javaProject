package ru.innopolis.service;

import java.net.MalformedURLException;

public interface ResourceGetter {
    String getResourceByPath(String path) throws MalformedURLException;
}
