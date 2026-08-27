package org.example.customerservice.customer.service;

import org.example.customerservice.customer.dto.CustomerResponse;
import org.example.customerservice.customer.dto.UpdateCustomerRequest;
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

    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        Customer customer = new Customer();

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setPassword(Encoder.hashPassword(request.password()));

        Customer savedCustomer = customerRepository.save(customer);
        return toResponse(savedCustomer);
    }

    public CustomerResponse getCustomerById(Long id) {
       Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kunden finns inte"));

       return toResponse(customer);
    }

    public CustomerResponse loginCustomer(LoginRequest request) {
        Customer customer = customerRepository.findByEmail(request.email());

        if (customer == null) {
            return null;
        }

        boolean correcPassword = Encoder.checkPassword(
                request.password(),
                customer.getPassword()
        );

        if (!correcPassword) {
            return null;
        }

        return toResponse(customer);
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    public CustomerResponse updateCustomer(Long customerId, UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Kunden finns inte"));
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setPhoneNumber(request.phoneNumber());

        Customer savedCustomer = customerRepository.save(customer);
        return toResponse(savedCustomer);

    }
}