package com.defer.backend.evaluation.api;

import com.defer.backend.evaluation.application.EvalApplicationService;
import com.defer.backend.evaluation.contracts.EvalRunPayload;
import com.defer.backend.evaluation.contracts.EvalRunResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/evals")
public class EvalController {

    private final EvalApplicationService service;

    public EvalController(EvalApplicationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EvalRunResponse persistEvalRun(@RequestBody EvalRunPayload payload) {
        return service.persistEvalRun(payload);
    }

    @GetMapping
    public List<EvalRunResponse> listEvalRuns() {
        return service.listEvalRuns();
    }

    @GetMapping("/{runId}")
    public EvalRunResponse getEvalRun(@PathVariable UUID runId) {
        return service.getEvalRun(runId);
    }
}
