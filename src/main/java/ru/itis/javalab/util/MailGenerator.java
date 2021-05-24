package ru.itis.javalab.util;

import ru.itis.javalab.model.User;

public interface MailGenerator {
    String getConfirmMail(String serverUrl, User user);
}
