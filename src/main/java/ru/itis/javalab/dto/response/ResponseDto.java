package ru.itis.javalab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {
    int type;
    T payload;
}
