package pl.piomin.services.redis.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.redis.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
