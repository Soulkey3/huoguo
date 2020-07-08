package cn.jsut.huoguo.web;


import cn.jsut.huoguo.pojo.Order;
import cn.jsut.huoguo.pojo.Product;
import cn.jsut.huoguo.pojo.Shopcar;
import cn.jsut.huoguo.pojo.User;
import cn.jsut.huoguo.service.OrderService;
import cn.jsut.huoguo.service.ProductService;
import cn.jsut.huoguo.service.ShopcarService;
import cn.jsut.huoguo.service.UserService;
import cn.jsut.huoguo.vo.OrderDetailVo;
import cn.jsut.huoguo.vo.ShopcarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端展示Controller
 */
@Controller
@RequestMapping("/front")
public class FrontController {


    @Autowired
    private ProductService productService;
    @Autowired
    private ShopcarService shopcarService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


    /**
     * 分类查询商品
     *
     * @param category
     * @param model
     * @return
     */
    @GetMapping("/findbycategory")
    public String findProductByCategory(String category, Model model) {
        System.out.println(category);
        List<Product> products = productService.findProductByCategory(category);
        model.addAttribute("products", products);
        return "frontproduct";
    }


    /**
     * 添加菜品数量接口
     *
     * @param productId
     * @param session
     * @return
     */
    @RequestMapping("/addshopcarcount")
    @ResponseBody
    public Map<String, Object> addshopcar(int productId, HttpSession session) {
        Map map = new HashMap();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        int flag = shopcarService.selectByUserIdAndProductId(userId, productId);
        if (flag == 0) {
            //如果该用户没有购物车，则给他添加购物车
            shopcarService.addShopcar(userId, productId);

        }
        if (flag > 0) {
            //如果该用户有商品则该商品数量加1
            Shopcar shopcar = shopcarService.findByUserIdAndProductId(userId, productId);
            Integer count = shopcar.getCount();
            count++;
            shopcarService.updateShopcarCount(shopcar.getShopcarId(), count);
        }
        Shopcar shopcar = shopcarService.findByUserIdAndProductId(userId, productId);
        map.put("shopcar", shopcar);
        return map;
    }


    /**
     * 减少菜品数量接口
     *
     * @param productId
     * @param session
     * @return
     */
    @RequestMapping("/cutshopcarcount")
    @ResponseBody
    public Map<String, Object> cutshopcarcount(int productId, HttpSession session) {
        Map map = new HashMap();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        //查找当前用户的购物车信息
        int flag = shopcarService.selectByUserIdAndProductId(userId, productId);
        if (flag > 0) {
            //如果该用户有商品则该商品数量减1
            Shopcar shopcar = shopcarService.findByUserIdAndProductId(userId, productId);
            Integer count = shopcar.getCount();
            count--;
            if (count == 0) {
                //数量为0清除出购车
                shopcarService.deleteShopcar(userId,productId);
                map.put("msg","1002");
                return map;
            }
            shopcarService.updateShopcarCount(shopcar.getShopcarId(), count);
        }
        Shopcar shopcar = shopcarService.findByUserIdAndProductId(userId, productId);
        map.put("shopcar", shopcar);
        return map;
    }


    /**
     * 购物车界面
     */
    @GetMapping("/shopcar")
    public String shopcarvo(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<ShopcarVo> shopcarVos = shopcarService.findShopcarVo(user.getUserId());
        System.out.println(shopcarVos);
        if (shopcarVos==null){//购物车无菜品，转去空页面
            return "shopcar-bank";
        }
            model.addAttribute("shopcarVos",shopcarVos);
            return "shopcar";
    }

    /**
     * 移除购物车中菜品
     */
    @RequestMapping("/deleteProduct/{shopcarId}")
    public String deleteProduct(@PathVariable("shopcarId") Integer shopcarId){
        shopcarService.deleteShopcarById(shopcarId);
        return "redirect:/front/shopcar";
    }



    /**
     * 个人订单列表
     */
    @RequestMapping("/userOrderList")
    public String userOrderList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Order> userOrders = orderService.findOrderListByUserId(user.getUserId());
        model.addAttribute("userOrders",userOrders);
        return "front-userOrderList";
    }

    /**
     *查询个人订单详情
     */
    @GetMapping("/findOrderById/{orderid}")
    public String findOrderById(@PathVariable("orderid") String orderid, Model model){
        Order order = orderService.findOrderById(orderid);
        User user = userService.findUserById(order.getUserId());
        List<OrderDetailVo> orderDetailsById = orderService.findOrderDetailById(orderid);
        int amount=0;//计算订单总商品
        for (OrderDetailVo orderDetailVo : orderDetailsById) {
            amount+=orderDetailVo.getCount();
        }
        model.addAttribute("amount",amount);
        model.addAttribute("order",order);
        model.addAttribute("user",user);
        model.addAttribute("ordersDetailById",orderDetailsById);
        return "front-orderdetail";
    }





}
