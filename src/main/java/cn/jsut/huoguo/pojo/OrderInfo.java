package cn.jsut.huoguo.pojo;


import lombok.Data;

/**
 * 订单
 */
@Data
public class OrderInfo {
    //订单详情id
    private Integer orderInfoId;
    //订单id
    private String orderId;
   //菜品id
    private Integer productId;
    //商品价格
    private Integer productPrice;
    //数量
    private Integer count;

}
