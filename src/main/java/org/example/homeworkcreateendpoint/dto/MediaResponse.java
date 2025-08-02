package org.example.homeworkcreateendpoint.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
        String name,
        String mimeTypeFile,
        String uri,
        Long size
) {
}