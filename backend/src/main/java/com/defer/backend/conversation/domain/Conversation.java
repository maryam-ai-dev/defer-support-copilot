package com.defer.backend.conversation.domain;

import java.time.Instant;
import java.util.UUID;

public class Conversation {

    private UUID id;
    private String channel;
    private UUID customerId;
    private ConversationStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public Conversation() {}

    public Conversation(UUID id, String channel, UUID customerId, ConversationStatus status,
                        Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.channel = channel;
        this.customerId = customerId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }

    public ConversationStatus getStatus() { return status; }
    public void setStatus(ConversationStatus status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
