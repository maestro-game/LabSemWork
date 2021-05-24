package ru.itis.javalab.dto.form;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    @Pattern(regexp = "^[-\\w]{1,63}$", message = "Id pattern mismatch")
    public String id;
    @Size(min = 12, message = "Too short password")
    @Size(max = 63, message = "Too long password")
    public String password;
    @Email(message = "Email pattern mismatch")
    public String email;
    @Pattern(regexp = "^[a-zA-Z]{1,63}$", message = "Name pattern mismatch")
    public String name;
    @Pattern(regexp = "^[a-zA-Z]{1,63}$", message = "Name pattern mismatch")
    public String surname;
    @Pattern(regexp = "^[\\d]{9,15}$", message = "Surname pattern mismatch")
    public String phone;
}
