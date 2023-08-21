package ra.academy.dao.impl;

import javafx.util.Builder;
import org.springframework.stereotype.Component;
import ra.academy.dao.IGenericDao;
import ra.academy.dto.request.FormLoginDto;
import ra.academy.model.User;
import ra.academy.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao implements IGenericDao<User,Long> {
    private final String FINDALL = "SELECT * FROM USERS";
    private final String FINDBYID = "SELECT * FROM USERS WHERE ID = ?";
    private final String INSERT = "INSERT INTO  USERS(username, password, full_name, avatar, role_id) values(?,?,?,?,?)";
    private final String UPDATE = "UPDATE USERS full_name=?, avatar=? where id = ?";
    private final String DELETE = "DELETE  FROM USERS WHERE ID =?";
    private final String LOGIN = "SELECT * FROM USERS WHERE username = ? and password = ?";

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn =null;
        try{
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall(FINDALL);
            ResultSet rs = callSt.executeQuery();

            while (rs.next()){

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(User user) {

        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            CallableStatement callSt = null;
            if(user.getId()==null){
                // chức năng thêm mới
                callSt=conn.prepareCall(INSERT);
                callSt.setString(1,user.getUsername());
                callSt.setString(2,user.getPassword());
                callSt.setString(3,user.getFullName());
                callSt.setString(4,user.getAvatar());
                callSt.setLong(5,user.getRoleId());

                // thực thi câu lệnh sql
                callSt.executeUpdate();
            }else {
                // cập nhật
                // thực thi câu lệnh sql
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }

    }

    @Override
    public User findById(Long id) {
        User user =null;
        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            CallableStatement callSt = conn.prepareCall(FINDBYID);
            // truyền đối số

            // thực thi câu lệnh xóa
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){

            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return user;
    }
    @Override
    public void delete(Long id){
        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            CallableStatement callSt = conn.prepareCall(DELETE);
            // truyền đối số

            // thực thi câu lệnh xóa
            callSt.executeUpdate();

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
    public User login (FormLoginDto formLoginDto){
        User user =null;
        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            CallableStatement callSt = conn.prepareCall(LOGIN);
            // truyền đối số
            callSt.setString(1,formLoginDto.getUsername());
            callSt.setString(2,formLoginDto.getPassword());
            // thực thi câu lệnh xóa
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                user = new User();
                        user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getLong("role_id"));

            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return user;
    }
}
