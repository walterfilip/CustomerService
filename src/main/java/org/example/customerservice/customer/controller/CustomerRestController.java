package org.example.customerservice.customer.controller;


import org.example.customerservice.customer.model.LoginRequest;
import org.example.customerservice.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.example.customerservice.customer.model.CreateCustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.customerservice.customer.dto.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(
            @RequestBody @Valid CreateCustomerRequest request
    ) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    //Kanske ska ha annan responsestatus
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);

    }

    @PostMapping("/login")
    public CustomerResponse login(
            @RequestBody LoginRequest request
    ) {
        return customerService.loginCustomer(request);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCustomerRequest request
    ){
        return customerService.updateCustomer(id, request);
    }

}
