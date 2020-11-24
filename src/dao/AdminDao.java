package dao;

import domain.Admin;

public interface AdminDao {
    Admin findUserByUsernameAndPassword(String username, String password);
}
