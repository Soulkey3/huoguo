package cn.jsut.huoguo.web;


import cn.jsut.huoguo.pojo.Order;
import cn.jsut.huoguo.pojo.User;
import cn.jsut.huoguo.service.OrderService;
import cn.jsut.huoguo.service.UserService;
import cn.jsut.huoguo.util.MathUtil;
import cn.jsut.huoguo.vo.ShopcarVo;
import com.alibaba.fastjson.JSON;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;


    /**
     * 订单提交前接口，接收购物车传来的值
     */
    @RequestMapping("/addPre")
    public String addPre(String orderInfoStr, Boolean useDiscount, Model model, HttpSession session) {
        System.out.println(useDiscount);
        int allRelief=0;//总减免默认不使用为0
        if (useDiscount) {//true代表使用积分减免
            //总减免 跟积分挂钩 减免价=积分量 1:1
            //在购物车界面写个接口点击确定 回显页面减免的价格，否则减免
            User user = (User) session.getAttribute("user");
            //重新去表中查当前user，保证数据的正确性
            User newUser = userService.findUserById(user.getUserId());
            allRelief = newUser.getPoint();
        }
        //我将订单总价和获得总积分放在了List<ShopcarVo>第一个元素里，直接取出用
        //把json字符串变回list，用Fastjson
        List<ShopcarVo> shopcarVos = JSON.parseArray(orderInfoStr, ShopcarVo.class);
        //获得订单总价
        Integer allCost = shopcarVos.get(0).getTotalPrice();
        //真实付款 总价-总减免
        int actuallyPaid = allCost - allRelief;
        model.addAttribute("actuallyPaid", actuallyPaid);
        model.addAttribute("allCost", allCost);
        model.addAttribute("allRelief", allRelief);
        model.addAttribute("cars", shopcarVos);
        model.addAttribute("orderInfoStr", orderInfoStr);
        return "order-addPre";
    }


    /**
     * 订单提交
     */
    @RequestMapping("/add")
    public String add(Order order, HttpSession session, String orderInfos, Model model) {

        List<ShopcarVo> shopcarVos = JSON.parseArray(orderInfos, ShopcarVo.class);

        String orderId = System.currentTimeMillis() + MathUtil.getRandomStr(3);
        order.setOrderId(orderId);//设置订单id
        order.setStatus("待付款");//设置付款状态
        order.setCreateTime(new Date());//设置订单创建时间
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getUserId());//设置用户id
        order.setPayType("支付宝");
        //添加订单
        Boolean flag = orderService.addOrder(order, shopcarVos);

        if (flag) {
            //如果订单成功，转去付款页面
            model.addAttribute("order", order);
            return "payPre";
        } else {
            //TODO 失败转去错误页面 页面没写
            return "errorPage";
        }

    }


    /**
     * 支付宝支付回调
     */
    @PostMapping("/notify")
    @ResponseBody
    public String asyncNotify(@RequestBody String notifyData) {
        return orderService.asyncNotify(notifyData);
    }


    /**
     * 调用支付
     */
    @PostMapping("/create")
    public ModelAndView create(Order webOrder, HttpServletRequest request, @RequestHeader("user-agent") String userAgent) {
        String payType = webOrder.getPayType();//先获取用户选择的支付方式

        //获取订单详情
        Order order = orderService.findOrderById(webOrder.getOrderId());

        PayResponse response = orderService.create(order.getOrderId(), order.getActualPayment(), BestPayTypeEnum.ALIPAY_PC);
        Map<String, String> map = new HashMap<>();
        map.put("body", response.getBody());

        return new ModelAndView("create", map);
    }


}
