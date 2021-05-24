package ru.itis.javalab.service;

import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import ru.itis.javalab.dto.contentsource.MainSourceDto;
import ru.itis.javalab.dto.contentsource.NewSourceDto;
import ru.itis.javalab.dto.contentsource.PreviewSourceDto;
import ru.itis.javalab.dto.contentsource.TitleSourceDto;
import ru.itis.javalab.dto.message.InnerMessageDto;
import ru.itis.javalab.model.User;

import java.util.List;
import java.util.Optional;

public interface ContentSourceService {
    List<PreviewSourceDto> findAllByUserId(String id);

    Optional<MainSourceDto> findByIdAndUser(String id, String userId, Pageable pageable);

    Optional<MainSourceDto> saveNew(NewSourceDto dto);

    boolean delete(String id, User user);

    List<TitleSourceDto> searchById(String id);

    Optional<InnerMessageDto> join(String id, String username, String message) throws NotFoundException;

    PreviewSourceDto findPreviewById(String id);
}
