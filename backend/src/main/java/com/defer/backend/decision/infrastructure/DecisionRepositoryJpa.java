package com.defer.backend.decision.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DecisionRepositoryJpa extends JpaRepository<DecisionLogEntity, UUID> {
    List<DecisionLogEntity> findByConversationIdOrderByCreatedAtDesc(UUID conversationId);
}
