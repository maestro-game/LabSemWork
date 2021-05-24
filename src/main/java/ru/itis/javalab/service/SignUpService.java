package ru.itis.javalab.service;

import ru.itis.javalab.dto.form.SignUpForm;

public interface SignUpService {
    String signUp(SignUpForm form);
    boolean confirm(String code);
}
