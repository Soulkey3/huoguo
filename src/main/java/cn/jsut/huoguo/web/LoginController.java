package cn.jsut.huoguo.web;

import cn.jsut.huoguo.pojo.Carousel;
import cn.jsut.huoguo.pojo.User;
import cn.jsut.huoguo.service.CarouselService;
import cn.jsut.huoguo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private CarouselService carouselService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Map<String, Object> map) {
        User user = userService.login(username, password);
        if (user == null) {
            //如果没有查询到用户返回登录界面并且带回提示 用户名或密码错误
            map.put("msg", "用户名或秘密错误");
            return "login";
        }
        session.setAttribute("user", user);
        Carousel carousel = carouselService.findCarousel();
        session.setAttribute("carousel",carousel);
        if(user.getUsername().equalsIgnoreCase("admin")){
            return "redirect:/toshopadmin";
        }
        return "index";
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(User user, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        System.out.println(user);
        if (userService.countByEmail(user.getEmail()) > 0) {
            map.put("msg", "该email已注册");
            return "register";
        }
        if (userService.countByUsername(user.getUsername()) > 0) {
            map2.put("msg2", "该用户名已被注册");
            return "register";
        }
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getBirthday().toString() == null) {
            map3.put("msg3", "请将用户信息填写完整");
            return "register";
        }
        userService.register(user);
        return "redirect:tologin";
    }


    /**
     * 登出系统
     *
     * @param session
     * @return
     */
    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:tologin";
    }


}
