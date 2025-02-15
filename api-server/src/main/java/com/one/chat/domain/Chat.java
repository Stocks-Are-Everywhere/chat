package com.one.chat.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "chat")
public class Chat {
    @Id
    long id;
    @Column
    long userId;
    @Column
    long groupId;
    @Column
    String content;
    @Column
    String username;
}
