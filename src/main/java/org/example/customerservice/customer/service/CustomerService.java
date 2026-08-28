package org.example.customerservice.customer.service;

import org.example.customerservice.customer.dto.CustomerResponse;
import org.example.customerservice.customer.dto.UpdateCustomerRequest2;
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


        //just nu går det skapa nytt konto på samma adress.
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

        if (!Encoder.checkPassword(
                request.password(),
                customer.getPassword()
        )) {
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

    public Customer updateProfile(UpdateCustomerRequest2 request) {
        Customer updatedCustomer = customerRepository.findByEmail(request.request().email());

        updatedCustomer.setFirstName(request.request().firstName());
        updatedCustomer.setLastName(request.request().lastName());
        updatedCustomer.setPhoneNumber(request.request().phoneNumber());

        if (request.changePassword()){
            updatedCustomer.setPassword(Encoder.hashPassword(request.request().password()));
        } else  {
            updatedCustomer.setPassword(request.request().password());
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

    public void removeUser(Customer customer) {
        customerRepository.delete(customer);
    }

//    public CustomerResponse updateCustomer(Long customerId, UpdateCustomerRequest request) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Kunden finns inte"));
//        customer.setFirstName(request.firstName());
//        customer.setLastName(request.lastName());
//        customer.setPhoneNumber(request.phoneNumber());
//
//        Customer savedCustomer = customerRepository.save(customer);
//        return toResponse(savedCustomer);
//
//    }
}