package ru.itis.javalab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.form.SignUpForm;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repository.UserRepository;
import ru.itis.javalab.util.EmailUtil;
import ru.itis.javalab.util.MailGenerator;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    final UserRepository userRepository;
    final Converter<SignUpForm, User> converter;
    final EmailUtil emailUtil;
    final MailGenerator mailGenerator;

    @Value("${server.email.from}")
    public String from;

    @Value("${server.email.subject}")
    public String subject;

    @Value("${server.url}")
    public String serverUrl;

    @Override
    public String signUp(SignUpForm form) {
        try {
            User user = converter.convert(form);
            user.setConfirm(UUID.randomUUID().toString());
            user = userRepository.save(user);
            emailUtil.sendMail(from, form.email, subject, mailGenerator.getConfirmMail(serverUrl, user));
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    @Override
    public boolean confirm(String code) {
        return userRepository.confirm(code) == 1;
    }
}
