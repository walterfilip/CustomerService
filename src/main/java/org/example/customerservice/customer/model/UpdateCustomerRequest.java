package org.example.customerservice.customer.model;



public record UpdateCustomerRequest(
        CreateCustomerRequest request,
        boolean changePassword
){

}

