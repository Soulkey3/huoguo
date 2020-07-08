package cn.jsut.huoguo.vo;

import cn.jsut.huoguo.pojo.OrderInfo;
import lombok.Data;

/**
 * 购物车页面展示视图
 */
@Data
public class OrderDetailVo extends OrderInfo {

    //订单详情id
    private Integer orderInfoId;
    //订单id
    private String orderId;
    //菜品id
    //private Integer productId;
    //商品价格
    //private Integer productPrice;
    //数量
    private Integer count;

    //商品Id
    private Integer productId;
    //商品名称
    private String productName;
    //价格
    private Integer productPrice;
    //图片路径
    private String imgAddr;
    //种类
    private String category;
    //商品描述
    private String productDesc;
    //商品积分
    private Integer productPoint;
    //商品优先级
    private Integer productPriority;

}
