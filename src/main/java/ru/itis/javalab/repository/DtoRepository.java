package ru.itis.javalab.repository;

import ru.itis.javalab.dto.contentsource.PreviewSourceDto;
import ru.itis.javalab.dto.message.RemoveMessageDto;

import java.util.List;

public interface DtoRepository {
    List<PreviewSourceDto> findAllPreviewSourceDtoByMember(String id);
    RemoveMessageDto findRemoveMessageDtoBySourceIdAndMessageId(String sourceId, Long messageId);

    PreviewSourceDto findPreviewSourceDtoById(String id);
}
