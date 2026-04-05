package com.defer.backend.policy.application;

import com.defer.backend.policy.contracts.SupportPolicyUpdateRequest;
import com.defer.backend.policy.domain.SupportPolicy;
import com.defer.backend.policy.infrastructure.PolicyRepositoryJpa;
import com.defer.backend.policy.infrastructure.SupportPolicyEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class PolicyApplicationService {

    private final PolicyRepositoryJpa repo;

    public PolicyApplicationService(PolicyRepositoryJpa repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public SupportPolicy getActivePolicy() {
        SupportPolicyEntity entity = repo.findByActiveTrue()
                .orElseThrow(() -> new IllegalStateException("No active support policy found"));
        return toDomain(entity);
    }

    @Transactional
    public SupportPolicy updatePolicy(SupportPolicyUpdateRequest request) {
        SupportPolicyEntity entity = repo.findByActiveTrue()
                .orElseThrow(() -> new IllegalStateException("No active support policy found"));

        if (request.escalationFrustrationThreshold() != null)
            entity.setEscalationFrustrationThreshold(request.escalationFrustrationThreshold());
        if (request.escalationEffortThreshold() != null)
            entity.setEscalationEffortThreshold(request.escalationEffortThreshold());
        if (request.escalationRepetitionCount() != null)
            entity.setEscalationRepetitionCount(request.escalationRepetitionCount());
        if (request.minConfidenceForDirectAnswer() != null)
            entity.setMinConfidenceForDirectAnswer(request.minConfidenceForDirectAnswer());
        if (request.requiresReviewConfidenceFloor() != null)
            entity.setRequiresReviewConfidenceFloor(request.requiresReviewConfidenceFloor());
        if (request.sensitiveTopicsEnabled() != null)
            entity.setSensitiveTopicsEnabled(request.sensitiveTopicsEnabled());

        entity.setUpdatedAt(Instant.now());
        repo.save(entity);
        return toDomain(entity);
    }

    private SupportPolicy toDomain(SupportPolicyEntity e) {
        SupportPolicy p = new SupportPolicy();
        p.setId(e.getId());
        p.setEscalationFrustrationThreshold(e.getEscalationFrustrationThreshold().doubleValue());
        p.setEscalationEffortThreshold(e.getEscalationEffortThreshold().doubleValue());
        p.setEscalationRepetitionCount(e.getEscalationRepetitionCount());
        p.setMinConfidenceForDirectAnswer(e.getMinConfidenceForDirectAnswer().doubleValue());
        p.setRequiresReviewConfidenceFloor(e.getRequiresReviewConfidenceFloor().doubleValue());
        p.setSensitiveTopicsEnabled(e.isSensitiveTopicsEnabled());
        p.setActive(e.isActive());
        p.setCreatedAt(e.getCreatedAt());
        p.setUpdatedAt(e.getUpdatedAt());
        return p;
    }
}
