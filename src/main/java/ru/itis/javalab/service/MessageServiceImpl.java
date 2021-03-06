package ru.itis.javalab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.dto.message.InnerMessageDto;
import ru.itis.javalab.dto.message.RemoveMessageDto;
import ru.itis.javalab.model.Channel;
import ru.itis.javalab.model.ContentSource;
import ru.itis.javalab.model.Message;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repository.ContentSourceRepository;
import ru.itis.javalab.repository.DtoRepository;
import ru.itis.javalab.repository.MessageRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ContentSourceRepository sourceRepository;
    private final DtoRepository dtoRepository;
    private final Converter<Message, InnerMessageDto> converter;

    @Override
    public Optional<InnerMessageDto> saveNew(String text, String userId, String sourceId) {
        if (sourceRepository.existsByMembersContainsAndId(User.builder().id(userId).build(), sourceId)) {
            var source = sourceRepository.findById(userId).get();
            if (!(source instanceof Channel) || !((Channel) source).getAdmin().getId().equals(userId)) {
                return messageRepository.findById(messageRepository.save(Message.builder()
                        .text(text)
                        .author(User.builder().id(userId).build())
                        .source(ContentSource.builder().id(sourceId).build())
                        .build()).getId()).map(converter::convert);
            }
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<InnerMessageDto> saveNewRepost(Long messageId, String userId, String sourceId) {
        if (sourceRepository.existsByMembersContainsAndId(User.builder().id(userId).build(), sourceId)) {
            var source = sourceRepository.findById(userId).get();
            if (!(source instanceof Channel) || !((Channel) source).getAdmin().getId().equals(userId)) {
                var message = messageRepository.findById(messageId).get();
                return messageRepository.findById(messageRepository.save(Message.builder()
                        .text(message.getText())
                        .author(User.builder().id(userId).build())
                        .source(ContentSource.builder().id(sourceId).build())
                        .from(message.getFrom() == null ? message.getAuthor() : message.getFrom())
                        .build()).getId()).map(converter::convert);
            }
        }
        return Optional.empty();
    }

    @Override
    public RemoveMessageDto delete(Long id, String userId, String sourceId) {
        return messageRepository.deleteByIdAndAuthorAndSource_Id(id, User.builder().id(userId).build(), sourceId) == 1 ?
                dtoRepository.findRemoveMessageDtoBySourceIdAndMessageId(sourceId, id) : null;
    }
}
