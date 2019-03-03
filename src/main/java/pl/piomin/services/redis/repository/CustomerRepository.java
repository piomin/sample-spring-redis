package pl.piomin.services.redis.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.redis.model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByExternalId(String externalId);
    List<Customer> findByAccountsId(Long id);

}
