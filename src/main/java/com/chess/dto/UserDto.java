package com.chess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long id;
    String username;
    String password;
    String email;
    boolean isOnline;
    String authenticationToken;
}
