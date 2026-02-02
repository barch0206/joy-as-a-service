package com.joy.dto;

public class ModerationRequest {
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