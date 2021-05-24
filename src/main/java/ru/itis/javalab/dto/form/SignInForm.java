package ru.itis.javalab.dto.form;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class SignInForm {
    @Email(message = "Email pattern mismatch")
    public String email;
    @Size(min = 12, message = "Too short password")
    @Size(max = 63, message = "Too long password")
    public String password;
}
