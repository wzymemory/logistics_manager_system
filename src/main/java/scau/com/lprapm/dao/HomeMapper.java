package scau.com.lprapm.dao;

import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.DangerGoods;

import java.util.List;

/**
 * Created by 62353 on 2017/3/13.
 */
@Repository
public interface HomeMapper {
    List<DangerGoods> getDangerGoodsByName(String dangerGoodsName);
}
