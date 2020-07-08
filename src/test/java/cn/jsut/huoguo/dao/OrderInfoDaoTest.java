package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.HuoguoApplicationTests;
import cn.jsut.huoguo.pojo.OrderInfo;
import cn.jsut.huoguo.vo.DailyCheckVo;
import cn.jsut.huoguo.vo.OrderDetailVo;
import cn.jsut.huoguo.vo.ProductCountVo;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class OrderInfoDaoTest extends HuoguoApplicationTests {

    @Resource
    private OrderInfoDao orderInfoDao;

    @Test
    public void insertOrderInfo() {
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setCount(1);
        orderInfo.setOrderId("00000001");
        orderInfo.setProductId(1);
        orderInfo.setProductPrice(50);
        int i = orderInfoDao.insertOrderInfo(orderInfo);
        System.out.println(i);
    }

    @Test
    public void findOrderDetail() {

        List<OrderDetailVo> orderDetail = orderInfoDao.findOrderDetail("1586434587763169");
        for (OrderDetailVo orderDetailVo : orderDetail) {
            System.out.println(orderDetailVo);
        }
    }

    @Test
    public void findDailyCheck() {
        List<DailyCheckVo> dailyCheck = orderInfoDao.findDailyCheck();
        for (DailyCheckVo dailyCheckVo : dailyCheck) {
            System.out.println(dailyCheckVo);
        }    }

    @Test
    public void findHotpotCount() {
        List<ProductCountVo> hotpotCount = orderInfoDao.findProductCount("蔬菜");
        for (ProductCountVo productCountVo : hotpotCount) {
            System.out.println(productCountVo);
        }

    }
}