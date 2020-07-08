package cn.jsut.huoguo.service;

import cn.jsut.huoguo.pojo.Carousel;
import org.springframework.transaction.annotation.Transactional;

public interface CarouselService {


    @Transactional
    void updateCarousel(Carousel carousel);

    Carousel findCarousel();
}
