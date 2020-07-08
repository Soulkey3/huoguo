package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.HuoguoApplicationTests;
import cn.jsut.huoguo.pojo.Shopcar;
import cn.jsut.huoguo.vo.ShopcarVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ShopcarDaoTest extends HuoguoApplicationTests {

    @Autowired
    private ShopcarDao shopcarDao;

    @Test
    public void selectByUserIdAndProductId() {
        int b = shopcarDao.selectByUserIdAndProductId(1, 1);
        System.out.println(b);

    }

    @Test
    public void addshopcar() {
        shopcarDao.addShopcar(1,1);

    }

    @Test
    public void findByUserIdAndProductId() {
        Shopcar byUserIdAndProductId = shopcarDao.findByUserIdAndProductId(1, 1);
        System.out.println(byUserIdAndProductId);

    }

    @Test
    public void updateShopcarCount() {

        shopcarDao.updateShopcarCount(1,10);
    }

    @Test
    public void findShopcarVo() {
        List<ShopcarVo> shopcarVo = shopcarDao.findShopcarVo(2);
        for (ShopcarVo vo : shopcarVo) {
            System.out.println(vo);
        }
    }

    @Test
    public void deleteShopcar() {
        shopcarDao.deleteShopcar(2,3);
    }

    @Test
    public void deleteShopcarById() {
        shopcarDao.deleteShopcarById(9);
    }

    @Test
    public void testFindShopcarVo() {
        List<ShopcarVo> shopcarVo = shopcarDao.findShopcarVo(2);
        if (shopcarVo.size()==0){
            System.out.println("我是空的");
        }

    }

    @Test
    public void deleteShopcarByUserId() {
        shopcarDao.deleteShopcarByUserId(1);
    }
}