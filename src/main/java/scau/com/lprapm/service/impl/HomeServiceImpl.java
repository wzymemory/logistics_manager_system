package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.HomeMapper;
import scau.com.lprapm.entity.DangerGoods;
import scau.com.lprapm.service.inter.HomeService;

import java.util.List;

/**
 * Created by 62353 on 2017/3/13.
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    HomeMapper homeMapper;
    @Override
    public List<DangerGoods> getDangerGoodsByName(String dangerGoodsName) {
        return homeMapper.getDangerGoodsByName(dangerGoodsName);
    }
}
