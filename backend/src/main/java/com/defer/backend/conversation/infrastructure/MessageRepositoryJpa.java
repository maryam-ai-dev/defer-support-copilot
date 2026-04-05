package com.defer.backend.conversation.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MessageRepositoryJpa extends JpaRepository<MessageEntity, UUID> {
    List<MessageEntity> findByConversationIdOrderByTurnIndexAsc(UUID conversationId);
    int countByConversationId(UUID conversationId);
}
