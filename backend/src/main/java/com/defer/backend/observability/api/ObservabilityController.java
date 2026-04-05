package com.defer.backend.observability.api;

import com.defer.backend.observability.application.TraceApplicationService;
import com.defer.backend.observability.infrastructure.TraceSpanEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/traces")
public class ObservabilityController {

    private final TraceApplicationService service;

    public ObservabilityController(TraceApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public List<TraceSpanEntity> getTraces(@RequestParam UUID conversationId) {
        return service.getTracesByConversation(conversationId);
    }

    @GetMapping("/{traceId}")
    public TraceSpanEntity getTrace(@PathVariable UUID traceId) {
        return service.getTrace(traceId);
    }
}
