package scau.com.lprapm.dao;

import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.PurPrice;
@Repository
public interface PurPriceMapper {
    int deleteByPrimaryKey(Integer purId);

    int insert(PurPrice record);

    int insertSelective(PurPrice record);

    PurPrice selectByPrimaryKey(Integer purId);

    int updateByPrimaryKeySelective(PurPrice record);

    int updateByPrimaryKey(PurPrice record);

    int insertPurPrice(PurPrice purPrice);
}