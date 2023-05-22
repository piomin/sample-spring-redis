package pl.piomin.services.redis;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class RedisContainerDevMode {

    @Bean
    @ServiceConnection("redis")
    GenericContainer redisContainer() {
        return new GenericContainer(DockerImageName.parse("redis").withTag("7.0"))
                .withExposedPorts(6379);
    }
}
