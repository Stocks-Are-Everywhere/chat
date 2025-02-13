package com.one.chat.dto;

import com.one.chat.domain.Chat;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateChatReq {
    long userId;
    long groupId;
    String content;
    public Chat of(){
        return Chat.builder()
                .userId(this.userId)
                .groupId(this.groupId)
                .content(this.content)
                .build();
    }
}
