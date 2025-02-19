package com.one.chat.service;

import com.one.chat.domain.Chat;
import com.one.chat.repo.ChatMongoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMongoRepo chatMongoRepo;
    public Chat createChatMongo(Chat chat){
        return chatMongoRepo.save(chat);
    }
}
