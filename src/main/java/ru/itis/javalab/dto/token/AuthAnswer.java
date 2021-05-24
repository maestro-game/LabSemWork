package ru.itis.javalab.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itis.javalab.dto.user.ProfileUserDto;

@Getter
@AllArgsConstructor
public class AuthAnswer {
    String token;
    ProfileUserDto user;
}
