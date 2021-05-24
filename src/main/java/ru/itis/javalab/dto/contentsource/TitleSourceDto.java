package ru.itis.javalab.dto.contentsource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class TitleSourceDto {
    String id;
    String name;
    Integer sourceType;
    @Setter String avatarImageUrl;
}
