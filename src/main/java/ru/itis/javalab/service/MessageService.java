package ru.itis.javalab.service;

import ru.itis.javalab.dto.message.InnerMessageDto;
import ru.itis.javalab.dto.message.RemoveMessageDto;

import java.util.Optional;

public interface MessageService {
    Optional<InnerMessageDto> saveNew(String text, String userId, String sourceId);

    Optional<InnerMessageDto> saveNewRepost(Long messageId, String userId, String sourceId);

    RemoveMessageDto delete(Long id, String userId, String channelId);
}
