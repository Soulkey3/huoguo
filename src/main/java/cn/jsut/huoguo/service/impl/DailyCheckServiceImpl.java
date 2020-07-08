package cn.jsut.huoguo.service.impl;

import cn.jsut.huoguo.dao.OrderInfoDao;
import cn.jsut.huoguo.service.DailyCheckService;
import cn.jsut.huoguo.vo.DailyCheckVo;
import cn.jsut.huoguo.vo.ProductCountVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DailyCheckServiceImpl implements DailyCheckService {
    @Resource
    private OrderInfoDao orderInfoDao;


    /**
     * 每个类别总销售额
     */
    @Override
    public List<DailyCheckVo> findDailyCheck() {
        return orderInfoDao.findDailyCheck();
    }

    /**
     *查询每个类别高销量
     */
    @Override
    public List<ProductCountVo> findProductCount(String category) {
        return orderInfoDao.findProductCount(category);
    }

}
