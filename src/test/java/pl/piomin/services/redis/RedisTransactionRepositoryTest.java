package pl.piomin.services.redis;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import pl.piomin.services.redis.model.Transaction;
import pl.piomin.services.redis.repository.TransactionRepository;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataRedisTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisTransactionRepositoryTest {

    @Autowired
    TransactionRepository repository;

    @Test
    @Order(1)
    void shouldAdd() {
        Transaction transaction = new Transaction(1L, 1000, new Date(), 20L, 40L);
        transaction = repository.save(transaction);
        assertNotNull(transaction);
    }

    @Test
    @Order(2)
    public void shouldFindByFromAccountId() {
        List<Transaction> transactions = repository.findByFromAccountId(20L);
        assertTrue(transactions.size() == 1);
    }

    @Test
    @Order(3)
    public void shouldFindByToAccountId() {
        List<Transaction> transactions = repository.findByToAccountId(40L);
        assertTrue(transactions.size() == 1);
    }

}
