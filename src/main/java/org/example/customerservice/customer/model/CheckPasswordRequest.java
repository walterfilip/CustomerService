package org.example.customerservice.customer.model;

public record CheckPasswordRequest(
        String password,
        String newPassword,
        String email
) {
}