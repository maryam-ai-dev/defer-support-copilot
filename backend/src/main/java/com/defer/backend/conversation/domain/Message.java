package com.defer.backend.conversation.domain;

import java.time.Instant;
import java.util.UUID;

public class Message {

    private UUID id;
    private UUID conversationId;
    private SenderType senderType;
    private String body;
    private int turnIndex;
    private Instant createdAt;

    public Message() {}

    public Message(UUID id, UUID conversationId, SenderType senderType, String body,
                   int turnIndex, Instant createdAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.senderType = senderType;
        this.body = body;
        this.turnIndex = turnIndex;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getConversationId() { return conversationId; }
    public void setConversationId(UUID conversationId) { this.conversationId = conversationId; }

    public SenderType getSenderType() { return senderType; }
    public void setSenderType(SenderType senderType) { this.senderType = senderType; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public int getTurnIndex() { return turnIndex; }
    public void setTurnIndex(int turnIndex) { this.turnIndex = turnIndex; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
