package com.defer.backend.integration.ai;

import com.defer.backend.integration.ai.dto.AiSupportRequest;
import com.defer.backend.integration.ai.dto.AiSupportResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
public class AiServiceClient {

    private final WebClient webClient;
    private final AiServiceProperties properties;

    public AiServiceClient(WebClient.Builder webClientBuilder, AiServiceProperties properties) {
        this.properties = properties;
        this.webClient = webClientBuilder
                .baseUrl(properties.getBaseUrl())
                .build();
    }

    public AiSupportResponse callSupportRespond(AiSupportRequest request) {
        return webClient.post()
                .uri("/api/v1/support/respond")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AiSupportResponse.class)
                .block(Duration.ofSeconds(properties.getTimeoutSeconds()));
    }
}
