package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.impl.UserDao;
import ra.academy.dto.request.FormLoginDto;
import ra.academy.dto.request.FormRegiterDto;
import ra.academy.model.User;
import ra.academy.service.IGenericService;

import java.util.List;
@Service
public class UserService  {
    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return null;
    }


    public User findById(Long id) {
        return null;
    }


    public void save(FormRegiterDto formRegiterDto) {
        // chuyen doi dto sang model
        User user = new User();
        user.setUsername(formRegiterDto.getUsername());
        user.setPassword(formRegiterDto.getPassword());
        user.setFullName(formRegiterDto.getFullname());
        userDao.save(user);
    }


    public void delete(Long id) {

    }
    public User login(FormLoginDto formLoginDto){
        return userDao.login(formLoginDto);
    }
}
