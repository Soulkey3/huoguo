package cn.jsut.huoguo.service;

import cn.jsut.huoguo.pojo.User;

import java.util.List;

public interface UserService {


    List<User> findAllUser();

    User findUserById(Integer userId);

    User login(String username,String password);

    int countByUsername(String username);
    int countByEmail(String email);

    void register(User user);

    void updatePoint(int point,int userId);

}
