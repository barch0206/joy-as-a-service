package com.joy.dto;
import jakarta.validation.constraints.NotNull;

public class ModerationRequest {
    @NotNull(message = "Joy ID is required for moderation")
    private Long id;

    // Default constructor for Jackson (JSON parser)
    public ModerationRequest() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}