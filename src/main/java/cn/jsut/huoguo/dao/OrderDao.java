package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;


@Mapper
public interface OrderDao {


    int insertOrder(Order order);

    @Select("SELECT * FROM `order` WHERE order_id=#{orderId}")
    Order selectbyId(@Param("orderId") String orderId);

    @Update("UPDATE `order` SET `pay_time`=#{payTime},`status`='已支付'  WHERE order_id=#{orderId}")
    int updateOrder(@Param("orderId")String OrderId,@Param("payTime") Date payTime);

    @Select("SELECT * FROM `order` WHERE `status`='已支付'; ")
    List<Order> findOrderList();

    @Select("SELECT * FROM `order` WHERE `status`='已支付' AND user_id=#{userId}; ")
    List<Order> findOrderListByUserId(@Param("userId") int userId);



}
