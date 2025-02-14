package com.one.chat.controller;

import com.one.chat.domain.Chat;
import com.one.chat.dto.CreateChatReq;
import com.one.chat.repo.ChatRepo;
import com.one.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SocketController {
    private final SimpMessageSendingOperations simpleMessageSendingOperations;
    private final ChatService chatService;

    /* URL에 channel 문자열을 감지하여 해당 메서드가 작동한다. 메세지 수신을 담당한다 */
    @MessageMapping("/channel")
    public void sendMessage(Map<String, Object> params) { /* 메세지 발행 메서드 */
        // 수신된 페이지를 저장
        Chat chat = Chat.builder()
                .groupId(Long.parseLong(params.get("groupId").toString()))
                .userId(1L)
                .content(params.get("content").toString())
                .username(params.get("userId").toString())
                .build();

        chatService.createChat(chat);

        /* 첫번째 인자에 사용된 문자열을 구독하는 모든 소비자에게 -> 두번째 인자 정보를 전달한다 */
        simpleMessageSendingOperations.convertAndSend("/sub/channel/" + params.get("groupId"), params);
    }
    @MessageMapping("/session-id")
    public void sendMessage(Map<String, Object> params, SimpMessageHeaderAccessor headerAccessor) {
        // WebSocket 세션 ID 가져오기
        String sessionId = headerAccessor.getSessionId();

        params.put("sessionId", sessionId);
        simpleMessageSendingOperations.convertAndSend("/sub/session-id", params);
    }
}
