package cn.jsut.huoguo.service.impl;


import cn.jsut.huoguo.dao.OrderDao;
import cn.jsut.huoguo.dao.OrderInfoDao;
import cn.jsut.huoguo.dao.ShopcarDao;
import cn.jsut.huoguo.dao.UserDao;
import cn.jsut.huoguo.pojo.Order;
import cn.jsut.huoguo.pojo.OrderInfo;
import cn.jsut.huoguo.pojo.User;
import cn.jsut.huoguo.service.OrderService;
import cn.jsut.huoguo.vo.OrderDetailVo;
import cn.jsut.huoguo.vo.ShopcarVo;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderInfoDao orderInfoDao;
    @Autowired
    private BestPayService bestPayService;
    @Resource
    private UserDao userDao;
    @Resource
    private ShopcarDao shopcarDao;


    /**
     *提交订单
     */
    @Override
    public Boolean addOrder(Order order, List<ShopcarVo> shopcarVos) {
        //编写订单业务逻辑
        int i = orderDao.insertOrder(order);
        int x=0;
        if (i==1){
            //插入订单详情
            for (ShopcarVo s : shopcarVos) {
                OrderInfo orderInfo=new OrderInfo();
                orderInfo.setOrderId(order.getOrderId());
                orderInfo.setProductId(s.getProductId());
                orderInfo.setProductPrice(s.getProductPrice());
                orderInfo.setCount(s.getCount());
                x+=orderInfoDao.insertOrderInfo(orderInfo);
            }
            if (x==shopcarVos.size()){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public Order findOrderById(String userId) {
        return orderDao.selectbyId(userId);
    }

    /**
     *创建支付
     */
    @Override
    public PayResponse create(String orderId, int amount, BestPayTypeEnum bestPayTypeEnum) {

        if (bestPayTypeEnum!= BestPayTypeEnum.ALIPAY_PC){
            throw new  RuntimeException("暂不支持的支付类型");
        }
        //3
        PayRequest request=new PayRequest();
        request.setOrderName("河底捞");
        request.setOrderId(orderId);
        request.setOrderAmount((double) amount);
        request.setPayTypeEnum(bestPayTypeEnum);

        //
        PayResponse reponse = bestPayService.pay(request);
        log.info("reponse={}",reponse);
        return reponse;

    }

    /**
     *支付宝支付完成后异步通知接受到钱款后处理
     * 1.修改支付状态：已付款
     * 2.如果选用积分付款，则扣除用户相应积分
     */
    @Override
    public String asyncNotify(String notifyData) {
        //1.签名检验
        PayResponse payResponse=bestPayService.asyncNotify(notifyData);
        //查询订单修改支付状态
        String orderId = payResponse.getOrderId();


        //查询出订单减免，相应更新用户的积分
        Order order = orderDao.selectbyId(orderId);
        User user = userDao.findUserById(order.getUserId());

        //本次消费可获积分
        Integer totalCost = order.getTotalCost();
        int addPoint=totalCost/10;

        //更新积分 最终积分=用户积分-使用积分+本次消费可获积分
        int point=user.getPoint()-order.getTotalDeduction()+addPoint;
        userDao.updatePoint(point,order.getUserId());

        //付款成功则 清空当前用户购物车
        shopcarDao.deleteShopcarByUserId(order.getUserId());

        System.out.println(orderId);
        System.out.println("付款成功");
        orderDao.updateOrder(orderId,new Date());
        log.info("payResponse={}",payResponse);
        return "success";//!必须为 ！success！支付宝才会确认接受到付款
    }

    @Override
    public List<Order> findOrderList() {
        return orderDao.findOrderList();
    }

    @Override
    public List<OrderDetailVo> findOrderDetailById(String orderId) {
        return orderInfoDao.findOrderDetail(orderId);
    }

    @Override
    public List<Order> findOrderListByUserId(int userId) {
        return orderDao.findOrderListByUserId(userId);
    }


}
