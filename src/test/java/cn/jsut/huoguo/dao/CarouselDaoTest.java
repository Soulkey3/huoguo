package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.HuoguoApplicationTests;
import cn.jsut.huoguo.pojo.Carousel;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class CarouselDaoTest extends HuoguoApplicationTests {

    @Resource
    private CarouselDao carouselDao;

    @Test
    public void updateCarousel() {
        Carousel carousel=new Carousel();
        carousel.setCarouselId(1);
        carousel.setCarouselImg1("111");
        carousel.setCarouselImg2("222");
        carousel.setCarouselImg3("333");
        carouselDao.updateCarousel(carousel);
    }


    @Test
    public void findCarousel() {
        Carousel carousel = carouselDao.findCarousel();
        System.out.println(carousel);


    }
}