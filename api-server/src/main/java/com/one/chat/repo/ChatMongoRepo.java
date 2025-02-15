package com.one.chat.repo;

import com.one.chat.domain.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMongoRepo extends MongoRepository<Chat, Long> {
}
