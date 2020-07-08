package cn.jsut.huoguo.service;

import cn.jsut.huoguo.pojo.Shopcar;
import cn.jsut.huoguo.vo.ShopcarVo;

import java.util.List;

public interface ShopcarService {


    int selectByUserIdAndProductId(int userId,int productId);

    void addShopcar(int userId,int productId);

    Shopcar findByUserIdAndProductId(int userId,int productId);

    void updateShopcarCount(int shopcarId,int count);

    List<ShopcarVo> findShopcarVo(int userId);

    void deleteShopcar(int userId,int productId);

    void deleteShopcarById(int shopcarId);

    void deleteShopcarByUserId(int userId);

}
