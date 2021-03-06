package ru.itis.javalab.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class ProfileUserDto {
    final String id;
    final String name;
    final String surname;
    final String email;
    final String phone;
    final String about;
    @Setter String photoUrl;
}
