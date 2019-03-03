package pl.piomin.services.redis.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.redis.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
