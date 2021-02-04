package pl.piomin.services.redis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
@Setter
public class Account {
    @Indexed private Long id;
    private String number;
    private int balance;
}
