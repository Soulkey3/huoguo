package cn.jsut.huoguo.vo;

import cn.jsut.huoguo.pojo.Shopcar;
import lombok.Data;

/**
 * 购物车页面展示视图
 */
@Data
public class ShopcarVo extends Shopcar {

    //购物车id
    private Integer shopcarId;
    //用户id
    private Integer userId;
    //商品id
    private Integer productId;
    //数量
    private Integer count;

    //菜品名称
    private String productName;
    //菜品单价
    private Integer productPrice;
    //菜品积分
    private Integer productPoint;
    //用户购物车总价
    private Integer totalPrice;
    //购物车总积分
    private Integer totalPoint;

}
