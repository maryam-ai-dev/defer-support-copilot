package com.defer.backend.observability.application;

import com.defer.backend.observability.infrastructure.ObservabilityRepositoryJpa;
import com.defer.backend.observability.infrastructure.TraceSpanEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TraceApplicationService {

    private final ObservabilityRepositoryJpa repo;
    private final ObjectMapper objectMapper;

    public TraceApplicationService(ObservabilityRepositoryJpa repo, ObjectMapper objectMapper) {
        this.repo = repo;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public UUID startTrace(UUID conversationId, String spanType, String spanName) {
        TraceSpanEntity entity = new TraceSpanEntity();
        entity.setId(UUID.randomUUID());
        entity.setConversationId(conversationId);
        entity.setSpanType(spanType);
        entity.setSpanName(spanName);
        entity.setStartedAt(Instant.now());
        repo.save(entity);
        return entity.getId();
    }

    @Transactional
    public void endTrace(UUID traceSpanId, Map<String, Object> metadata) {
        TraceSpanEntity entity = repo.findById(traceSpanId)
                .orElseThrow(() -> new IllegalArgumentException("TraceSpan not found: " + traceSpanId));
        entity.setEndedAt(Instant.now());
        if (metadata != null && !metadata.isEmpty()) {
            try {
                entity.setMetadataJson(objectMapper.writeValueAsString(metadata));
            } catch (JsonProcessingException e) {
                entity.setMetadataJson("{}");
            }
        }
        repo.save(entity);
    }

    @Transactional(readOnly = true)
    public List<TraceSpanEntity> getTracesByConversation(UUID conversationId) {
        return repo.findByConversationIdOrderByStartedAtAsc(conversationId);
    }

    @Transactional(readOnly = true)
    public TraceSpanEntity getTrace(UUID traceId) {
        return repo.findById(traceId)
                .orElseThrow(() -> new IllegalArgumentException("TraceSpan not found: " + traceId));
    }
}
