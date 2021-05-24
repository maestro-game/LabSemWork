package ru.itis.javalab.dto.contentsource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.model.User;

@Getter
public class NewSourceDto {
    String name;
    String id;
    String about;
    MultipartFile image;
    Integer sourceType;
    Integer type;
    @Setter User admin;
}
