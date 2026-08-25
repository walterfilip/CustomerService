package org.example.customerservice.customer.service;

import jakarta.validation.Valid;
import org.example.customerservice.customer.model.*;
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


        //just nu går det skapa nytt konto på samma adress.
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

    public Customer updateProfile(UpdateCustomerRequest request) {
        Customer updatedCustomer = customerRepository.findByEmail(request.getRequest().email());
        System.out.println( "förnamn före:" + updatedCustomer.getFirstName());
        updatedCustomer.setFirstName(request.getRequest().firstName());
        System.out.println("request förnamn: " + request.getRequest().firstName());
        System.out.println("updatedcustomersförnamn efter: " + updatedCustomer.getFirstName());
        updatedCustomer.setLastName(request.getRequest().lastName());
        updatedCustomer.setPhoneNumber(request.getRequest().phoneNumber());

        if (request.isChangePassword()){
            updatedCustomer.setPassword(Encoder.hashPassword(request.getRequest().password()));
        } else  {
            updatedCustomer.setPassword(request.getRequest().password());
        }
        customerRepository.save(updatedCustomer);
        return  updatedCustomer;
    }
    public boolean checkPassword(CheckPasswordRequest passwordRequest) {

        if (passwordRequest.password() == null || passwordRequest.password().isBlank()) {
            return false;
        }
        if (passwordRequest.newPassword() == null || passwordRequest.newPassword().isBlank()) {
            return false;
        }
        Customer customer = customerRepository.findByEmail(passwordRequest.email());
        return Encoder.checkPassword(passwordRequest.password(), customer.getPassword());
    }
}