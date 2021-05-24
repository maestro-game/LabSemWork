package ru.itis.javalab.util;

public interface EmailUtil {
    void sendMail(String from, String to, String subject, String text);
}
