package pl.piomin.services.redis.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.redis.model.Transaction;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByFromAccountId(Long fromAccountId);
    List<Transaction> findByToAccountId(Long toAccountId);

}
