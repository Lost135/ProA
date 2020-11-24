package dao;

import domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll();

    void adduser(User user);

    void deluser(int id);

    User findById(int i);

    void update(User user);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
