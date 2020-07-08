package cn.jsut.huoguo.pojo;


import lombok.Data;

import java.util.Date;

/**
 * 订单
 */
@Data
public class Order {
    //订单id
    private String orderId;
    //用户id
    private Integer userId;
    //总价
    private Integer totalCost;
    //创建时间
    private Date createTime;
    //付款时间
    private Date payTime;
    //用餐桌号
    private Integer address;
    //付款方式，支付宝？微信？
    private String payType;
    //状态，已付款？待付款？
    private String status;
    //实际付款
    private Integer  actualPayment;
    //总减免
    private Integer totalDeduction;

}
