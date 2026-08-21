package org.example.customerservice.customer.service;

import org.example.customerservice.customer.model.CreateCustomerRequest;
import org.example.customerservice.customer.model.Customer;
import org.example.customerservice.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomerRequest request) {

        Customer savedCustomer = new Customer(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.phoneNumber(),
                request.password());

        return customerRepository.save(savedCustomer);
    }
}