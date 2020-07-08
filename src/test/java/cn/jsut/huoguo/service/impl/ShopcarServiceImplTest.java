package cn.jsut.huoguo.service.impl;

import cn.jsut.huoguo.HuoguoApplicationTests;
import cn.jsut.huoguo.service.ShopcarService;
import cn.jsut.huoguo.vo.ShopcarVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ShopcarServiceImplTest extends HuoguoApplicationTests {

    @Autowired
    private ShopcarService shopcarService;

    @Test
    public void findShopcarVo() {
        List<ShopcarVo> shopcarVo = shopcarService.findShopcarVo(2);


        for (ShopcarVo vo : shopcarVo) {
            System.out.println(vo);
        }
    }

    @Test
    public void testFindShopcarVo() {
        List<ShopcarVo> shopcarVo = shopcarService.findShopcarVo(2);
      if (shopcarVo==null){
            System.out.println("我是空的");
        }

    }
}