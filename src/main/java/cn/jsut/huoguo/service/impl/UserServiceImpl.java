package cn.jsut.huoguo.service.impl;

import cn.jsut.huoguo.dao.UserDao;
import cn.jsut.huoguo.pojo.User;
import cn.jsut.huoguo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;




    /**
     * 查找所有用户信息
     * @return
     */
    @Override
    @Transactional
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId);
    }

    @Override
    @Transactional
    public User login(String username, String password) {
        User user = userDao.findUserByUsername(username);
        if (user!=null&user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public int countByUsername(String username) {
        return userDao.countUserByUsername(username);
    }

    @Override
    public int countByEmail(String email) {
        return userDao.countUserByemail(email);
    }

    @Override
    @Transactional
    public void register(User user) {
        userDao.addUser(user);
    }

    /**
     * 更新用户的积分
     */
    @Override
    public void updatePoint(int point, int userId) {
        userDao.updatePoint(point, userId);
    }


}
