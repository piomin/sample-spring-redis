package pl.piomin.services.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.piomin.services.redis.model.Account;
import pl.piomin.services.redis.model.Customer;
import pl.piomin.services.redis.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@DataRedisTest
public class RedisRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testAdd() {
        Customer customer = new Customer(1L, "John Smith");
        customer.addAccount(new Account(1L, "1234567890", 2000));
        customer.addAccount(new Account(2L, "1234567891", 4000));
        customer = repository.save(customer);
        Assert.assertNotNull(customer);
    }

}
