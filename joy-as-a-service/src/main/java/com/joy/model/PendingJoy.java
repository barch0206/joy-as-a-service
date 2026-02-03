package com.joy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pending_joys")
public class PendingJoy {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Content cannot be empty")
    @Size(min = 3, max = 280, message = "Joy must be between 3 and 280 characters")
    @Column(nullable = false)
    private String content;

    @Column(name = "submitted_at", insertable = false, updatable = false)
    @JsonIgnore
    private OffsetDateTime submittedAt;

    // Default Constructor
    public PendingJoy() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}