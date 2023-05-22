package pl.piomin.services.redis;

import org.springframework.boot.SpringApplication;

public class SampleSpringRedisApplicationTest {

    public static void main(String[] args) {
        SpringApplication.from(SampleSpringRedisApplication::main)
                .with(RedisContainerDevMode.class)
                .run(args);
    }
}
