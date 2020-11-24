package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<User> findAll() {
        String sql = "select * from userlist";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public void adduser(User user) {
        String sql = "insert into userlist values(null,?,?,?,?,?,?)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void deluser(int id) {
        String sql = "delete from userlist where id = ?";
        template.update(sql, id);
    }

    @Override
    public User findById(int i) {
        String sql = "select * from userlist where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), i);

    }

    @Override
    public void update(User user) {
        String sql = "update userlist set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from userlist where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);

        Set<String> keySet = condition.keySet();

        List<Object> params = new ArrayList<Object>();
        for(String key : keySet){
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)){
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }


        return template.queryForObject(sb.toString(), Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from userlist where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);

        Set<String> keySet = condition.keySet();

        List<Object> params = new ArrayList<Object>();
        for(String key : keySet){
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)){
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }

        sb.append((" limit ?, ? "));

        if(start <0){
            start = 0;
        }

        params.add(start);
        params.add(rows);
        sql = sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
