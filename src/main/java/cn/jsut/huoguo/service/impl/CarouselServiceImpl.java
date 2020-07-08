package cn.jsut.huoguo.service.impl;

import cn.jsut.huoguo.dao.CarouselDao;
import cn.jsut.huoguo.pojo.Carousel;
import cn.jsut.huoguo.service.CarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselDao carouselDao;


    @Override
    public void updateCarousel(Carousel carousel) {
        carouselDao.updateCarousel(carousel);
    }

    @Override
    public Carousel findCarousel() {
        return carouselDao.findCarousel();
    }
}
