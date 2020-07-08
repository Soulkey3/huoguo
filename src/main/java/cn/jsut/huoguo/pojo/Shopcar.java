package cn.jsut.huoguo.pojo;

import lombok.Data;

/**
 * 购物车实体类
 */
@Data
public class Shopcar {

    //购物车id
    private Integer shopcarId;
    //用户id
    private Integer userId;
    //商品id
    private Integer productId;
    //数量
    private Integer count;

}
