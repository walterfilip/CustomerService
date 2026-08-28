package org.example.customerservice.customer.controller;


import org.example.customerservice.customer.model.*;
import org.example.customerservice.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.example.customerservice.customer.model.CreateCustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public CustomerResponse createCustomer(
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

    @PostMapping("/login")
    public ResponseEntity<Customer> loginCustomer(@RequestBody LoginRequest request) {
        Customer customer = customerService.loginCustomer(request);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(customer);
    }
//    @PostMapping("/login")
//    public CustomerResponse login(
//            @RequestBody LoginRequest request
//    ) {
//        return customerService.loginCustomer(request);
//    }

    @PostMapping("/update")
    public Customer updateCustomer(@RequestBody @Valid UpdateCustomerRequest request){
        return customerService.updateProfile(request);
    }
//    @PutMapping("/{id}")
//    public CustomerResponse updateCustomer(
//            @PathVariable Long id,
//            @RequestBody @Valid UpdateCustomerRequest request
//    ){
//        return customerService.updateCustomer(id, request);
//    }


    @PostMapping("/checkpassword")
    public boolean changePassword(@RequestBody CheckPasswordRequest passwordRequest){
        return customerService.checkPassword(passwordRequest);
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestBody Customer customer){
        customerService.removeUser(customer);
        return "Deleted";
    }



}
