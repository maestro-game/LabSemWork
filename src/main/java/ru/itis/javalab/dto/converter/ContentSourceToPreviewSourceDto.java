package ru.itis.javalab.dto.converter;//package ru.itis.javalab.dto.converter;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import ru.itis.javalab.dto.contentsource.PreviewSourceDto;
//import ru.itis.javalab.model.ContentSource;
//
//@Component
//public class ContentSourceToPreviewSourceDto implements Converter<ContentSource, PreviewSourceDto> {
//    @Override
//    public PreviewSourceDto convert(ContentSource source) {
//        return PreviewSourceDto.builder()
//                .id(source.getId())
//                .name(source.getName())
//                .lastMessageShortText(source.getMessages().get(0).getText())
//                .lastMessageTimestamp(source.getMessages().get(0).getCreated())
//                .build();
//    }
//}
