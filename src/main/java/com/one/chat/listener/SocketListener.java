package com.one.chat.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class SocketListener {
    @EventListener
    /* 소켓 연결 성공 반환 메서드 */
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        System.out.println("Received a new web socket connection");
    }

    @EventListener
    /* 소켓 해제 성공 반환 메서드 */
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        System.out.println("Received a new web socket disconnection");
    }
}
