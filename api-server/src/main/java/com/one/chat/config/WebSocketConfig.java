package com.one.chat.config;

import com.one.chat.handler.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    final StompHandler stompHandler;

    /* 소켓 엔드 포인트 등록 */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*");
                //.withSockJS();
    }
    /* 메세지 브로커 설정 */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /* 구독 URL 설정. /sub이 포함되면 구독으로 인식 */
        registry.enableSimpleBroker("/sub");

        /* 발행 URL 설정. /pub이 포함되면 발행으로 인식 */
        registry.setApplicationDestinationPrefixes("/pub");
    }

    /* 인터셉터 설정 */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }
}
