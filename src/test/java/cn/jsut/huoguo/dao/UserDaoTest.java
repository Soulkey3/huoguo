package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.HuoguoApplicationTests;
import cn.jsut.huoguo.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserDaoTest extends HuoguoApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void findUserByUsername() {
        User test = userDao.findUserByUsername("test");
        System.out.println(test);
    }

    @Test
    public void testCountUser(){
        int i = userDao.countUserByemail("21211");
        int j = userDao.countUserByUsername("test");
        System.out.println(i);
        System.out.println(j);
    }

    @Test
    public void testadduser(){
        User user=new User();
        user.setUsername("test2");
        user.setPassword("111");
        Date date=new Date();
        //user.setBirthday(date.getTime());
        user.setEmail("test2@qq.com");
        userDao.addUser(user);

    }

    @Test
    public void updatePoint() {
        userDao.updatePoint(10,3);
    }
}