package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.DangerGoods;

import java.util.List;

/**
 * Created by 62353 on 2017/3/13.
 */
public interface HomeService {
    List<DangerGoods> getDangerGoodsByName(String dangerGoodsName);
}
