package com.chess.service.validator;

import com.chess.dto.UserDto;
import com.chess.service.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
public class UserDtoValidator {

    public static final String REGULAR_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public boolean isValidFields(UserDto userDto) throws ValidationException {
        isValidPassword(userDto.getPassword());
        isValidEmail(userDto.getEmail());
        isValidUsername(userDto.getUsername());
        return true;
    }

    public boolean isValidPassword(String password) throws ValidationException {
        if (password == null || password.equals("")) {
            log.warn("password not valid");
            throw new ValidationException("Password not valid!");
        }
        return true;
    }

    public boolean isValidEmail(String email) throws ValidationException {
        boolean answer = Pattern.compile(REGULAR_PATTERN).matcher(email)
                .matches();
        if (!answer) {
            throw new ValidationException("Email not valid!");
        }
        return true;
    }

    public boolean isValidUsername(String username) throws ValidationException {
        if (username.contains("@")) {
            throw new ValidationException("Login not valid!");
        }
        return true;
    }
}
