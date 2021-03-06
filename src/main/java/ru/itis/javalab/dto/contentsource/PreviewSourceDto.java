package ru.itis.javalab.dto.contentsource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Builder
public class PreviewSourceDto {
    String id;
    String name;
    String lastMessageShortText;
    Timestamp lastMessageTimestamp;
    @Setter String avatarImageUrl;

    public PreviewSourceDto(String id, String name, String lastMessageShortText, Timestamp lastMessageTimestamp) {
        this.id = id;
        this.name = name;
        this.lastMessageShortText = lastMessageShortText;
        this.lastMessageTimestamp = lastMessageTimestamp;
    }
}
