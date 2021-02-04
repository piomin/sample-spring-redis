package pl.piomin.services.redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.redis.model.Customer;
import pl.piomin.services.redis.model.Transaction;
import pl.piomin.services.redis.repository.CustomerRepository;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") Long id) {
        Optional<Customer> optCustomer = repository.findById(id);
        return optCustomer.orElse(null);
    }

}
