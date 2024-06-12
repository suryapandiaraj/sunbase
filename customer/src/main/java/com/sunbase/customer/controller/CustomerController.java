package com.sunbase.customer.controller;

import com.sunbase.customer.exceptions.ResourceNotFoundException;
import com.sunbase.customer.entity.Customer;
import com.sunbase.customer.entity.User;
import com.sunbase.customer.repository.UserRepository;
import com.sunbase.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserRepository userRepository;

    // create customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer , Authentication authentication){
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return customerService.createCustomer(customer, user);
    }

    // edit customer
    @PutMapping("/{id}")
    public Customer editCustomer(@PathVariable Long id, @RequestBody Customer customerDetails, Authentication authentication){
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return customerService.editCustomer(id, customerDetails, user);
    }

    // get all customer
    @GetMapping
    public Page<Customer> getAllCustomers(Authentication authentication,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy){
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return customerService.getAllCustomers(user, page, size, sortBy);
    }

    // get customer by id
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id, Authentication authentication){
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return customerService.getCustomerById(id, user);
    }

    // delete customer by id
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id, Authentication authentication){
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->new ResourceNotFoundException("User not found"));
        customerService.deleteCustomer(id, user);
        return "Customer deleted.";
    }
}
