package org.example.customerservice.customer.service;

import org.example.customerservice.customer.model.CreateCustomerRequest;
import org.example.customerservice.customer.model.Customer;
import org.example.customerservice.customer.model.LoginRequest;
import org.example.customerservice.customer.repository.CustomerRepository;
import org.example.customerservice.utils.encoder.Encoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomerRequest request) {

        Customer customer = new Customer();

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPhoneNumber(request.phoneNumber());

        customer.setPassword(Encoder.hashPassword(request.password()));

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kunden finns inte"));

    }

    public Customer loginCustomer(LoginRequest request) {
        Customer customer = customerRepository.findByEmail(request.email());

        if (customer == null) {
            return null;
        }

        if (!Encoder.checkPassword(
                request.password(),
                customer.getPassword()
        )) {
            return null;
        }

        return customer;
    }

}