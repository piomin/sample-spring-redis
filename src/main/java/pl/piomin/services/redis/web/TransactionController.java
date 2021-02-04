package pl.piomin.services.redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.redis.model.Transaction;
import pl.piomin.services.redis.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionRepository repository;

    @PostMapping
    public Transaction add(@RequestBody Transaction transaction) {
        return repository.save(transaction);
    }

    @GetMapping("/{id}")
    public Transaction findById(Long id) {
        Optional<Transaction> optTransaction = repository.findById(id);
        return optTransaction.orElse(null);
    }

    @GetMapping("/from/{accountId}")
    public List<Transaction> findByFromAccountId(Long accountId) {
        return repository.findByFromAccountId(accountId);
    }

    @GetMapping("/to/{accountId}")
    public List<Transaction> findByToAccountId(Long accountId) {
        return repository.findByToAccountId(accountId);
    }

}
