package ru.itis.javalab.dto.contentsource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import ru.itis.javalab.dto.message.InnerMessageDto;

@Getter
@Builder
public class MainSourceDto {
    String about;
    String id;
    String name;
    Integer sourceType;
    @Setter Page<InnerMessageDto> messages;
    Integer subsAmount;
    String avatarImageUrl;
}
