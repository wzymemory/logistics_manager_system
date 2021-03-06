package scau.com.lprapm.dao;

import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Goods;
@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int insertGoods(Goods goods);
}