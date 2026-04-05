package com.defer.backend.evaluation.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EvalRunRepositoryJpa extends JpaRepository<EvalRunEntity, UUID> {
}
