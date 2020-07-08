package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.pojo.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CarouselDao {

    @Update("UPDATE `carousel` SET `carousel_img1`=#{carouselImg1},`carousel_img2`=#{carouselImg2},`carousel_img3`=#{carouselImg3} WHERE `carousel_id`=1;")
    void updateCarousel(Carousel carousel);

    @Select("SELECT * FROM `carousel` WHERE `carousel_id`=1;")
    Carousel findCarousel();
}
