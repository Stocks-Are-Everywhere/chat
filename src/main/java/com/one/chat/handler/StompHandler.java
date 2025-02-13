package com.one.chat.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class StompHandler implements ChannelInterceptor {

    @Override
    public void postSend(Message message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();
        System.out.println("Command : " + accessor.getCommand());

        switch ((accessor.getCommand())) {
            case CONNECT ->
                    System.out.println("✅ STOMP CONNECT 감지! 세션 ID: " + sessionId);
            case DISCONNECT ->
                    System.out.println("❌ STOMP DISCONNECT 감지! 세션 ID: " + sessionId);
            default -> {}

        }
    }


}
