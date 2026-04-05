package com.defer.backend.policy.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PolicyRepositoryJpa extends JpaRepository<SupportPolicyEntity, UUID> {
    Optional<SupportPolicyEntity> findByActiveTrue();
}
