package service.impl;
import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import domain.Admin;
import service.AdminService;

public class AdminServiceImpl implements AdminService {

    private AdminDao dao =  new AdminDaoImpl();

    @Override
    public Admin login(Admin admin) {
        return dao.findUserByUsernameAndPassword(admin.getUsername(),admin.getPassword());
    }

}
