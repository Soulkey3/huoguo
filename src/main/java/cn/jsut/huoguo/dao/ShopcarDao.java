package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.pojo.Shopcar;
import cn.jsut.huoguo.vo.ShopcarVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 购物车
 */
@Mapper
public interface ShopcarDao {

    /**
     * 根据userid和商品id查询用户是否有购物车
     * 返回数量
     */
    @Select("select count(*) from shopcar where user_id=${userId} and product_id=${productId}")
    int selectByUserIdAndProductId(@Param("userId") int userId,@Param("productId") int productId);


    /**
     * 插入购物车记录
     */
    @Insert("insert into shopcar(user_id,product_id) values (${userId},${productId}) ")
    void addShopcar(@Param("userId") int userId,@Param("productId") int productId);

    /**
     * 查找购物车
     */
    @Select("select * from shopcar where user_id=${userId} and product_id=${productId}")
    Shopcar findByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);


    /**
     * 更新购物车商品的数量
     */
    @Update("update shopcar set count=${count} where shopcar_id=${shopcarId}" )
    void updateShopcarCount(@Param("shopcarId") int shopcarId,@Param("count") int count);


    /**
     * 查询详细购物车
     */
    @Select("SELECT * FROM shopcar s,product p WHERE s.product_id=p.product_id AND user_id=${userId}")
    List<ShopcarVo> findShopcarVo(@Param("userId") int userId);


    /**
     * 删除购物车记录，用于减少商品数量的controller
     */
    @Delete("delete from shopcar where user_id=${userId} and product_id=${productId}")
    void deleteShopcar(@Param("userId") int userId,@Param("productId") int productId);

    /**
     * 根据id删除购物车记录，用于删除购物车界面的菜品
     */
    @Delete("delete from shopcar where shopcar_id=#{shopcarId} ")
    void deleteShopcarById(@Param("shopcarId") int shopcarId);

    /**
     * 根据用户id删除购物车内容
     */
    @Delete("delete from shopcar where user_id=#{userId} ")
    void deleteShopcarByUserId(@Param("userId") int userId);


}
