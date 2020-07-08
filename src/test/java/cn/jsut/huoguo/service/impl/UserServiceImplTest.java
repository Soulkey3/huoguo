package cn.jsut.huoguo.service.impl;

import cn.jsut.huoguo.HuoguoApplicationTests;
import cn.jsut.huoguo.pojo.User;
import cn.jsut.huoguo.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserServiceImplTest extends HuoguoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void findAllUser() {
        List<User> allUser = userService.findAllUser();
        for (User user : allUser) {
            System.out.println(user.toString());
        }
    }


}