package cn.jsut.huoguo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.jsut.huoguo.dao")
@EnableTransactionManagement  //开启事务控制
public class HuoguoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuoguoApplication.class, args);
    }

}
