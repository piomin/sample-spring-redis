package pl.piomin.services.redis;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import pl.piomin.services.redis.model.Account;
import pl.piomin.services.redis.model.Customer;
import pl.piomin.services.redis.repository.CustomerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataRedisTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisCustomerRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Test
    @Order(1)
    void shouldAdd() {
        Customer customer = new Customer(1L, "80010121098", "John Smith");
        customer.addAccount(new Account(1L, "1234567890", 2000));
        customer.addAccount(new Account(2L, "1234567891", 4000));
        customer.addAccount(new Account(3L, "1234567892", 6000));
        customer = repository.save(customer);
        assertNotNull(customer);
    }

    @Test
    @Order(2)
    void shouldFindByAccounts() {
        List<Customer> customers = repository.findByAccountsId(3L);
        assertEquals(1, customers.size());
        Customer customer = customers.get(0);
        assertNotNull(customer);
        assertEquals(1, customer.getId().longValue());
    }

    @Test
    @Order(3)
    void shouldFindByExternal() {
        Customer customer = repository.findByExternalId("80010121098");
        assertNotNull(customer);
        assertEquals(1, customer.getId().longValue());
    }
}
