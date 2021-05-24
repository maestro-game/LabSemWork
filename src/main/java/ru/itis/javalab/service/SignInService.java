package ru.itis.javalab.service;

import ru.itis.javalab.dto.form.SignInForm;
import ru.itis.javalab.dto.token.AuthAnswer;
import ru.itis.javalab.exception.UserIsBannedException;
import ru.itis.javalab.exception.UserNotFoundException;

public interface SignInService {
    AuthAnswer signIn(SignInForm form) throws UserIsBannedException, UserNotFoundException;
}
