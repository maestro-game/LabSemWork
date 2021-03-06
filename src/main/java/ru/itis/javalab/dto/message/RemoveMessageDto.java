package ru.itis.javalab.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RemoveMessageDto {
    Long id;
    Timestamp lastMessageTimestamp;
    String lastMessageShortText;
}
