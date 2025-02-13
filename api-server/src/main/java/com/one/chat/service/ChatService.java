package com.one.chat.service;

import com.one.chat.domain.Chat;
import com.one.chat.dto.CreateChatReq;
import com.one.chat.repo.ChatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepo chatRepo;

    public Chat createChat(Chat chat){
        return chatRepo.save(chat);
    }
}
