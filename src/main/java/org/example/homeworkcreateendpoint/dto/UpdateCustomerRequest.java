package org.example.homeworkcreateendpoint.dto;

public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}