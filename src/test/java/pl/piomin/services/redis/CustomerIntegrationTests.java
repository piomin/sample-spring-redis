package pl.piomin.services.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.piomin.services.redis.model.Account;
import pl.piomin.services.redis.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CustomerIntegrationTests {

    @Autowired
    TestRestTemplate template;

    @Container
    @ServiceConnection
    static final GenericContainer redis = new GenericContainer("redis:latest")
            .withExposedPorts(6379);

    @Test
    void testAddAndFind() {
        assertTrue(redis.isRunning());
        Customer customer = new Customer(1L, "123456", "John Smith");
        customer.addAccount(new Account(1L, "1234567890", 2000));
        customer.addAccount(new Account(2L, "1234567891", 4000));
        customer = template.postForObject("/customers", customer, Customer.class);
        assertNotNull(customer);
        customer = template.getForObject("/customers/{id}", Customer.class, 1L);
        assertNotNull(customer);
        assertEquals("123456", customer.getExternalId());
        assertEquals(2, customer.getAccounts().size());
    }

}
