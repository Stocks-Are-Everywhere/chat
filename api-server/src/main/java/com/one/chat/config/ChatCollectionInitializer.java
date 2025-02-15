package com.one.chat.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatCollectionInitializer {
    @Bean
    public CommandLineRunner initCollection(MongoTemplate mongoTemplate) {
        return args -> {
            String collectionName = "chat";
            if (!mongoTemplate.collectionExists(collectionName)) {
                mongoTemplate.createCollection(collectionName);
                System.out.println("✅ '" + collectionName + "' 컬렉션이 생성되었습니다.");
            }
        };
    }

}
