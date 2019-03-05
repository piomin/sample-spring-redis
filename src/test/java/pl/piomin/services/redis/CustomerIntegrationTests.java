package pl.piomin.services.redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import pl.piomin.services.redis.model.Account;
import pl.piomin.services.redis.model.Customer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CustomerIntegrationTests {

    @Autowired
    TestRestTemplate template;

    @ClassRule
    public static GenericContainer redis = new GenericContainer("redis:5.0.3").withExposedPorts(6379);

    @Before
    public void init() {
        int port = redis.getFirstMappedPort();
        System.setProperty("spring.redis.host", String.valueOf(port));
    }

    @Test
    public void testAddAndFind() {
        Customer customer = new Customer(1L, "123456", "John Smith");
        customer.addAccount(new Account(1L, "1234567890", 2000));
        customer.addAccount(new Account(2L, "1234567891", 4000));
        customer = template.postForObject("/customers", customer, Customer.class);
        Assert.assertNotNull(customer);
        customer = template.getForObject("/customers/{id}", Customer.class, 1L);
        Assert.assertNotNull(customer);
        Assert.assertEquals("123456", customer.getExternalId());
        Assert.assertEquals(2, customer.getAccounts().size());
    }

}
