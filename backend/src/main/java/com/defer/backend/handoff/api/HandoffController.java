package com.defer.backend.handoff.api;

import com.defer.backend.handoff.application.HandoffApplicationService;
import com.defer.backend.handoff.domain.HandoffPacket;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class HandoffController {

    private final HandoffApplicationService service;

    public HandoffController(HandoffApplicationService service) {
        this.service = service;
    }

    @GetMapping("/handoffs/{handoffId}")
    public HandoffPacket getHandoff(@PathVariable UUID handoffId) {
        return service.getHandoff(handoffId);
    }

    @GetMapping("/case-files/{caseFileId}/handoff")
    public HandoffPacket getHandoffByCaseFile(@PathVariable UUID caseFileId) {
        return service.getHandoffByCaseFile(caseFileId);
    }
}
