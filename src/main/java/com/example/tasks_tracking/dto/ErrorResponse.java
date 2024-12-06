package com.example.tasks_tracking.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
