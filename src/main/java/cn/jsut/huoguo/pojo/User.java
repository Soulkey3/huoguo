package cn.jsut.huoguo.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {

    //用户id
    private Integer userId;
    //用户名
    private String username;
    //密码
    private String password;
    //出生日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    //邮箱
    private String email;
    //积分
    private Integer point;

}
