package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.HuoguoApplicationTests;
import cn.jsut.huoguo.pojo.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class OrderDaoTest extends HuoguoApplicationTests {

    @Resource
    private OrderDao orderDao;

    @Test
    public void insertOrder() {
        Order order=new Order();
        order.setOrderId("00000000002");
        order.setUserId(1);
        order.setCreateTime(new Date());
        order.setPayTime(null);
        order.setAddress(1);
        order.setPayType(null);
        order.setStatus("待付款");
        order.setActualPayment(100);
        order.setTotalDeduction(0);
        int i = orderDao.insertOrder(order);
        System.out.println(i);
    }

    @Test
    public void selectbyId() {
        Order order = orderDao.selectbyId("1586434587763169");
        System.out.println(order);
    }

    @Test
    public void updateOrder() {

        int i = orderDao.updateOrder("0000001", new Date());
        System.out.println(i);
    }

    @Test
    public void findOrderList() {
        List<Order> orderList = orderDao.findOrderList();
        for (Order order : orderList) {
            System.out.println(order);
        }

    }

    @Test
    public void findOrderListByUserId() {
        List<Order> orderListByUserId = orderDao.findOrderListByUserId(2);
        for (Order order : orderListByUserId) {
            System.out.println(order);
        }


    }
}