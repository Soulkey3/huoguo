package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * 用户表持久层
 */
@Mapper
public interface UserDao {

    /**
     * 更新用户积分
     */
    @Update("UPDATE `user` SET `point`=${point} WHERE `user_id`=${userId};")
    void updatePoint(@Param("point") int point,@Param("userId") int userId);

    /**
     * 查询所有用户
     */
    @Select("select * from user order by point desc")
    List<User> findAllUser();

    /**
     * 根据id查询用户
     */
    @Select("select * from user where user_id=${userId}")
    User findUserById(@Param("userId") Integer userId);


    /**
     * 根据username查询用户
     */
    //select * from user where username=${username}
    @Select("select * from user where username='${username}'")
    User findUserByUsername(@Param("username") String username);

    //************用于注册校验，不能有同名，同邮箱的账户***********************
    /**
     * 根据email查询用户,返回个数
     */
    //select * from user where username=${username}
    @Select("select count(*) from user where email='${email}'")
    int countUserByemail(@Param("email") String email);

    /**
     * 根据username查询用户,返回个数
     */
    //select * from user where username=${username}
    @Select("select count(*) from user where username='${username}'")
    int countUserByUsername(@Param("username") String username);

    /*******************************************************************************

    /**
     * 添加用户
     */
    void addUser(User user);


}
