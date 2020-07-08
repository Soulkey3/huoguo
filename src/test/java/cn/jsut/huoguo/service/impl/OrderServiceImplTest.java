package cn.jsut.huoguo.service.impl;

import cn.jsut.huoguo.HuoguoApplicationTests;
import cn.jsut.huoguo.dao.OrderInfoDao;
import cn.jsut.huoguo.vo.OrderDetailVo;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceImplTest extends HuoguoApplicationTests {
    @Resource
    private OrderInfoDao orderInfoDao;

    @Test
    public void findOrderDetailById() {
        List<OrderDetailVo> orderDetail = orderInfoDao.findOrderDetail("1586434807430783");
        for (OrderDetailVo orderDetailVo : orderDetail) {
            System.out.println(orderDetailVo);
        }
    }
}