package ru.itis.javalab.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.user.ProfileUserDto;
import ru.itis.javalab.model.User;

@Component
public class UserToProfileUserDto implements Converter<User, ProfileUserDto> {

    @Override
    public ProfileUserDto convert(User user) {
        return new ProfileUserDto(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhone(),
                user.getAbout());
    }
}
