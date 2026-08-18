package org.example.customerservice.customer.controller;

import org.example.customerservice.customer.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {


    @GetMapping("/userid")
    public Customer getAciveUserId(){
        Customer test = new Customer("Nisse", "Swagson","swagmail@hot.se", "0761243124", "swag" );

        return test;
    }



//    Customer customer = getCustomerFromAPI();
//    CreateCustomerRequest request2 = new CreateCustomerRequest(
//            customer.getFirstName(),
//            customer.getLastName(),
//            customer.getEmail(),
//            customer.getPhoneNumber(),
//            customer.getPassword()
//    );
//    Customer customer1 = customerService.createCustomer(request2);
}
