package org.example.customerservice.customer.model;

public record LoginRequest(
        String email,
        String password
) {
}