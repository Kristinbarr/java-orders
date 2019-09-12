package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // GET http://localhost:2019/customer/order - returns ALL customers with their orders
    @GetMapping(value = "/customer/order", produces = {"application/json"})
    public ResponseEntity<?> getAllCustomersAndOrders() {
        List<Customer> customerList = customerService.findAll();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    // GET http://localhost:2019/customer/name/{custname} - returns all orders for a particular customer
    @GetMapping(value = "/customer/name/{custname}",
            produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(@PathVariable String custname) {
        Customer c = customerService.findCustomerByName(custname);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // POST http://localhost:2019/data/customer/new - adds a new customer including new orders
    @PostMapping(value = "/data/customers/new",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody
                                                        Customer customer) throws URISyntaxException {
        customer = customerService.save(customer);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{custcode}").buildAndExpand(customer.getCustcode()).toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/data/customer/update/{custcode} - updated a particular custcode, orders not needed
    @PutMapping(value = "/data/customer/update/{custcode}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> updateRestaurant(
            @RequestBody Customer updateCustomer,
            @PathVariable long custcode) {
        customerService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/data/customer/delete/{custcode} - delete a particular customer and their orders
    @DeleteMapping(value = "/data/customer/delete/{custcode}")
    public ResponseEntity<?> deleteRestaurantById(
            @PathVariable long custcode) {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
