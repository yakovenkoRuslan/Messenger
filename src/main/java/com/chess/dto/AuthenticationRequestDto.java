package com.chess.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    String username;
    String password;
}
