package pl.piomin.services.redis;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.piomin.services.redis.model.Account;
import pl.piomin.services.redis.model.Transaction;
import pl.piomin.services.redis.repository.TransactionRepository;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataRedisTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisTransactionRepositoryTest {

    @Autowired
    TransactionRepository repository;

    @Test
    public void testAdd() {
        Transaction transaction = new Transaction(1L, 1000, new Date(), 20L, 40L);
        transaction = repository.save(transaction);
        Assert.assertNotNull(transaction);
    }

    @Test
    public void testFindByFromAccountId() {
        List<Transaction> transactions = repository.findByFromAccountId(20L);
        Assert.assertTrue(transactions.size() == 1);
    }

    @Test
    public void testFindByToAccountId() {
        List<Transaction> transactions = repository.findByToAccountId(40L);
        Assert.assertTrue(transactions.size() == 1);
    }

}
