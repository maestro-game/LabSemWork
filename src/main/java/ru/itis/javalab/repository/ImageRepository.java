package ru.itis.javalab.repository;

import java.io.InputStream;

public interface ImageRepository {
    String get(Long id);

    void save(InputStream inputStream, Long id);
}
