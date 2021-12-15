package com.chess.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/friends")
public class FriendController {

    @GetMapping
    public ResponseEntity friend() {
        Map<Object, Object> ans = new HashMap<>();
        ans.put("val", "swcbwebcfw");
        return ResponseEntity.ok(ans);
    }
}
