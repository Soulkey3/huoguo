package cn.jsut.huoguo.service.impl;


import cn.jsut.huoguo.dao.ShopcarDao;
import cn.jsut.huoguo.pojo.Shopcar;
import cn.jsut.huoguo.service.ShopcarService;
import cn.jsut.huoguo.vo.ShopcarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopcarServiceImpl implements ShopcarService {

    @Autowired
    private ShopcarDao shopcarDao;

    @Override
    public int selectByUserIdAndProductId(int userId, int productId) {
        return shopcarDao.selectByUserIdAndProductId(userId,productId);
    }

    @Override
    public void addShopcar(int userId, int productId) {
        shopcarDao.addShopcar(userId,productId);
    }

    @Override
    public Shopcar findByUserIdAndProductId(int userId, int productId) {
        return shopcarDao.findByUserIdAndProductId(userId,productId);
    }

    @Override
    public void updateShopcarCount(int shopcarId, int count) {
        shopcarDao.updateShopcarCount(shopcarId,count);
    }

    @Override
    public List<ShopcarVo> findShopcarVo(int userId) {
        List<ShopcarVo> shopcarVo = shopcarDao.findShopcarVo(userId);
        if (shopcarVo.size()==0){
            return null;
        }
        //计算总价和总积分
        int totalprice=0;
        int totalpoint=0;
        for (ShopcarVo vo : shopcarVo) {
            totalprice+=vo.getCount()*vo.getProductPrice();
            totalpoint+=vo.getProductPoint();

        }
        //将购物车总价格和总积分暂时存在第一个元素内
        shopcarVo.get(0).setTotalPrice(totalprice);
        shopcarVo.get(0).setTotalPoint(totalpoint);
        return shopcarVo;
    }

    @Override
    public void deleteShopcar(int userId, int productId) {
        shopcarDao.deleteShopcar(userId,productId);
    }

    @Override
    public void deleteShopcarById(int shopcarId) {
        shopcarDao.deleteShopcarById(shopcarId);
    }

    /**
     * 根据用户id删除购物车元祖，用于付款后清空该用户的购物车
     * @param userId
     */
    @Override
    public void deleteShopcarByUserId(int userId) {
        shopcarDao.deleteShopcarByUserId(userId);
    }
}
