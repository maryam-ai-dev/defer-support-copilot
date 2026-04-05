package com.defer.backend.casefile.contracts;

public record RecordOpenQuestionRequest(
        String questionText,
        String source
) {}
