package ru.itis.javalab.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void save(MultipartFile multipartFile);
    //get;
    //String getUrl;
}
