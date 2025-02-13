package com.one.chat.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {
    @GetMapping("/api/session-id")
    public ResponseEntity<String> getSessionId(HttpServletRequest request) {
        // 현재 클라이언트의 세션 ID 반환
        String sessionId = request.getSession().getId();
        return ResponseEntity.ok(sessionId);
    }
}
