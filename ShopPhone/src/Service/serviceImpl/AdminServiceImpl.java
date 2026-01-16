package Service.serviceImpl;

import Repository.daoImpl.AdminRepository;
import Service.service.AdminService;

public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminImpl;

    public AdminServiceImpl(AdminRepository adminImpl) {
        this.adminImpl = adminImpl;
    }

    @Override
    public boolean login(String username, String password) {

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            System.out.println("Username hoặc password không được để trống");
            return false;
        }

        try {
            adminImpl.checkIn(username.trim(), password.trim());
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
