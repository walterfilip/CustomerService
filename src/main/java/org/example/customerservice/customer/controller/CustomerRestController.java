package org.example.customerservice.customer.controller;


import org.example.customerservice.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.example.customerservice.customer.model.CreateCustomerRequest;
import org.example.customerservice.customer.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(
            @RequestBody @Valid CreateCustomerRequest request
    ) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    //Kanske ska ha annan responsestatus
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);

    }



}
