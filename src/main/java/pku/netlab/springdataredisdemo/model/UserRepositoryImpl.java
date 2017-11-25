package pku.netlab.springdataredisdemo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String KEY_PREFIX = UserRepositoryImpl.class.getName();
    private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private RedisTemplate<String, User> redisTemplate;
    private HashOperations<String, String, User> hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        hashOperations.put(KEY_PREFIX, user.getId(), user);
    }

    @Override
    public List<User> findAll() {
        Map<String, User> map = hashOperations.entries(KEY_PREFIX);
        List<User> res = new ArrayList<>(map.size());
        res.addAll(map.values());
        return res;
    }

    @Override
    public User findById(String id) {
        logger.debug("Find user by id:{}", id);
        return hashOperations.entries(KEY_PREFIX).get(id);
    }

    @Override
    public void update(User u) {
        save(u);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY_PREFIX, id);
    }
}
