package pku.netlab.springdataredisdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import pku.netlab.springdataredisdemo.model.User;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringDataRedisDemoApplication {
    @Value("${REDIS.HOST}")
    private String redisHost;
    @Value("${REDIS.PORT}")
    private int redisPort;
    @Value("${REDIS.PASSWORD}")
    private String redisPassword;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(redisHost);
        connectionFactory.setPort(redisPort);
        connectionFactory.setPassword(redisPassword);
        return connectionFactory;
    }

    @Bean
    RedisTemplate<String, User> redisTemplate() {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisDemoApplication.class, args);
    }
}
