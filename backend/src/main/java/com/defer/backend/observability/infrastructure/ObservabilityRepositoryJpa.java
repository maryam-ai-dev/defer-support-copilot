package com.defer.backend.observability.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ObservabilityRepositoryJpa extends JpaRepository<TraceSpanEntity, UUID> {
    List<TraceSpanEntity> findByConversationIdOrderByStartedAtAsc(UUID conversationId);
}
