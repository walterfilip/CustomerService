package org.example.customerservice.customer.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest2(
        @NotBlank(message = "Förnamn måste anges") String firstName,
        @NotBlank(message = "Efternamn måste anges") String lastName,
        @NotBlank(message = "Telefonnummer måste anges") String phoneNumber) {
}
