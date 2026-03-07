package pl.piomin.services.redis.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.redis.model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByExternalId(String externalId);

    default List<Customer> findByAccountsId(Long id) {
        List<Customer> all = (List<Customer>) findAll();
        return all.stream()
                .filter(c -> c.getAccounts().stream()
                        .anyMatch(a -> a.getId().equals(id)))
                .toList();
    }

}
