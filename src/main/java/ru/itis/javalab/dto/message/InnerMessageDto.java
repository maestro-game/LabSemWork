package ru.itis.javalab.dto.message;

import lombok.Builder;
import lombok.Getter;
import ru.itis.javalab.dto.contentsource.TitleSourceDto;

import java.sql.Timestamp;

@Getter
@Builder
public class InnerMessageDto {
    Long id;
    Timestamp created;
    TitleSourceDto author;
    TitleSourceDto from;
    String text;
    Integer views;
}
