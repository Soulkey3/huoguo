package cn.jsut.huoguo.service;


import cn.jsut.huoguo.vo.DailyCheckVo;
import cn.jsut.huoguo.vo.ProductCountVo;

import java.util.List;

public interface DailyCheckService {


    List<DailyCheckVo> findDailyCheck();

    List<ProductCountVo> findProductCount(String category);

}
