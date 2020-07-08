package cn.jsut.huoguo.service;

import cn.jsut.huoguo.pojo.Order;
import cn.jsut.huoguo.vo.OrderDetailVo;
import cn.jsut.huoguo.vo.ShopcarVo;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    @Transactional //开启事务
    Boolean addOrder(Order order, List<ShopcarVo> shopcarVos);

    Order findOrderById(String userId);

    /**
     *创建发起支付
     */
    @Transactional
    PayResponse create(String orderId, int amount, BestPayTypeEnum bestPayTypeEnum);

    /**
     * 异步通知处理
     */
    @Transactional
    String asyncNotify(String notifyData);

    List<Order> findOrderList();

    List<OrderDetailVo> findOrderDetailById(String orderId);

    List<Order> findOrderListByUserId(int userId);


}
