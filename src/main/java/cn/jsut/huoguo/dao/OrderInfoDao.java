package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.pojo.OrderInfo;
import cn.jsut.huoguo.vo.DailyCheckVo;
import cn.jsut.huoguo.vo.OrderDetailVo;
import cn.jsut.huoguo.vo.ProductCountVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderInfoDao {

    @Insert("INSERT INTO \n" +
            "`order_info` (order_info_id,order_id,product_id,product_price,`count`)\n" +
            "VALUES  \n" +
            "(#{orderInfoId},#{orderId},#{productId},#{productPrice},#{count});")
    @Options(useGeneratedKeys = true,keyColumn = "order_info_id",keyProperty = "orderInfoId")
    int insertOrderInfo(OrderInfo orderInfo);

    @Select("SELECT * FROM `order_info` oi, `product` p  WHERE p.`product_id`=oi.`product_id` and order_id=#{orderId};")
    List<OrderDetailVo> findOrderDetail(@Param("orderId") String orderId);


    @Select("SELECT `category`,SUM(o.`product_price`*`count`) `amount` FROM `product` p,`order_info` o WHERE p.`product_id`=o.`product_id` GROUP BY  `category`;")
    List<DailyCheckVo> findDailyCheck();

    @Select("SELECT p.`product_name`,SUM(o.`count`) `count` \n" +
            "FROM  `product` p,`order_info` o\n" +
            "WHERE p.`product_id`=o.`product_id` AND p.`category`=#{category} \n" +
            "GROUP BY p.`product_name`\n" +
            "ORDER BY 2 DESC;")
    List<ProductCountVo> findProductCount(@Param("category") String category);


}
