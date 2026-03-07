package pl.piomin.services.redis;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.redis.test.autoconfigure.DataRedisTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.piomin.services.redis.model.Account;
import pl.piomin.services.redis.model.Customer;
import pl.piomin.services.redis.repository.AccountRepository;
import pl.piomin.services.redis.repository.CustomerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataRedisTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class RedisCustomerRepositoryTest {

    @Container
    @ServiceConnection
    static final GenericContainer redis = new GenericContainer("redis:latest")
            .withExposedPorts(6379);

    @Autowired
    CustomerRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    @Order(1)
    void shouldAdd() {
        Account account1 = new Account(1L, "1234567890", 2000);
        Account account2 = new Account(2L, "1234567891", 4000);
        Account account3 = new Account(3L, "1234567892", 6000);
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        Customer customer = new Customer(1L, "80010121098", "John Smith");
        customer.addAccount(account1);
        customer.addAccount(account2);
        customer.addAccount(account3);
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
