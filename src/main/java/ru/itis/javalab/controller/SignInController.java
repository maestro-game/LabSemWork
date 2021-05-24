package ru.itis.javalab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.form.SignInForm;
import ru.itis.javalab.exception.UserIsBannedException;
import ru.itis.javalab.exception.UserNotFoundException;
import ru.itis.javalab.service.SignInService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signIn")
public class SignInController {
    final SignInService signInService;

    @PostMapping
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInForm signInForm) {
        try {
            return ResponseEntity.ok(signInService.signIn(signInForm));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (UserIsBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
