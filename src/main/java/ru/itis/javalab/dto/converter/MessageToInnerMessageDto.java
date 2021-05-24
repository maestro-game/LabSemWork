package ru.itis.javalab.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.contentsource.TitleSourceDto;
import ru.itis.javalab.dto.message.InnerMessageDto;
import ru.itis.javalab.model.ContentSource;
import ru.itis.javalab.model.Message;

@Component
@RequiredArgsConstructor
public class MessageToInnerMessageDto implements Converter<Message, InnerMessageDto> {
    private final Converter<ContentSource, TitleSourceDto> converter;

    @Override
    public InnerMessageDto convert(Message source) {
        return InnerMessageDto.builder()
                .id(source.getId())
                .text(source.getText())
                .author(source.getAuthor() != null ? converter.convert(source.getAuthor()) : null)
                .created(source.getCreated())
                .from(source.getFrom() != null ? converter.convert(source.getFrom()) : null)
                .build();
    }
}
