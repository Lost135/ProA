package dao.impl;

import dao.AdminDao;
import domain.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class AdminDaoImpl implements AdminDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Admin findUserByUsernameAndPassword(String username, String password) {

        try {
            String sql = "select * from admin where username = ? and password = ?";
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), username, password);
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
