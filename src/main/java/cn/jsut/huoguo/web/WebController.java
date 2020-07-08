package cn.jsut.huoguo.web;

import cn.jsut.huoguo.pojo.Carousel;
import cn.jsut.huoguo.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 处理各种页面的转发控制类
 */
@Controller
public class WebController {

    @Autowired
    private CarouselService carouselService;

    @RequestMapping("/toshopadmin")
    public String toShopAdmin(){
        return "shopadmin";
    }

    @RequestMapping("/toaddproduct")
    public String toAddProduct(){
        return "addproduct";
    }

    @RequestMapping("/toindex")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/toindex2")
    public String toIndex2(HttpSession session){
        Carousel carousel = carouselService.findCarousel();
        session.setAttribute("carousel",carousel);
        return "index2";
    }

    @RequestMapping("/tologin")
    public String toLogin(Model model){
        return "login";
    }

    @RequestMapping("/toregister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/toshopcar")
    public String toShopcar(){
        return "shopcar";
    }

    @RequestMapping("/tocarousel")
    public String toCarousel(){
        return "back-carousel";
    }

    @RequestMapping("/tofrontchat")
    public String toFontChat(){
        return "front-chat";
    }

    @RequestMapping("/tobackchat")
    public String toBackChat(){
        return "back-chat";
    }

}
