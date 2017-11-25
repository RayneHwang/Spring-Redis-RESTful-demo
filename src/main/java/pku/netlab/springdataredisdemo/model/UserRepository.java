package pku.netlab.springdataredisdemo.model;

import java.util.List;

public interface UserRepository {
    void save(User user);

    List<User> findAll();

    User findById(String id);

    void update(User u);

    void delete(String id);
}
