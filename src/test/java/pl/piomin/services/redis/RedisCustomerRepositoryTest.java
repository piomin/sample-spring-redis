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
import pl.piomin.services.redis.model.Customer;
import pl.piomin.services.redis.repository.CustomerRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@DataRedisTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisCustomerRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testAdd() {
        Customer customer = new Customer(1L, "80010121098", "John Smith");
        customer.addAccount(new Account(1L, "1234567890", 2000));
        customer.addAccount(new Account(2L, "1234567891", 4000));
        customer.addAccount(new Account(3L, "1234567892", 6000));
        customer = repository.save(customer);
        Assert.assertNotNull(customer);
    }

    @Test
    public void testFindByAccounts() {
        List<Customer> customers = repository.findByAccountsId(3L);
        Assert.assertEquals(1, customers.size());
        Customer customer = customers.get(0);
        Assert.assertNotNull(customer);
        Assert.assertEquals(1, customer.getId().longValue());
    }

    @Test
    public void testFindByExternal() {
        Customer customer = repository.findByExternalId("80010121098");
        Assert.assertNotNull(customer);
        Assert.assertEquals(1, customer.getId().longValue());
    }
}
