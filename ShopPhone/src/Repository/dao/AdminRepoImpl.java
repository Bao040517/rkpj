package Repository.dao;

import Utils.ConnectionOpen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AdminRepoImpl implements Repository.daoImpl.AdminRepository {
    @Override
    public void checkIn(String username, String password) {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = ConnectionOpen.getConnection();
            stmt = conn.prepareCall("{CALL check_in(?, ?)}");

            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            if (!rs.next()) {
                throw new RuntimeException("Sai username hoặc password");
            }

            int adminId = rs.getInt("admin_id");
            String adminUsername = rs.getString("username");

            System.out.println(
                    "Đăng nhập thành công - ID: "
                            + adminId + ", username: " + adminUsername
            );

        } catch (Exception e) {
            throw new RuntimeException("Đăng nhập thất bại", e);
        } finally {
            ConnectionOpen.closeConnection(conn, stmt);
        }

    }
}
