package com.defer.backend.cases;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cases")
public class CasesController {

    private final CasesService service;

    public CasesController(CasesService service) {
        this.service = service;
    }

    @GetMapping
    public List<CaseListResponse> listCases(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean escalationCandidate,
            @RequestParam(required = false) Double minEffortScore) {
        return service.listCases(status, escalationCandidate, minEffortScore);
    }
}
