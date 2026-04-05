package com.defer.backend.conversation.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ConversationRepositoryJpa extends JpaRepository<ConversationEntity, UUID> {
}
