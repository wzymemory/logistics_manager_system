package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.DistributionOrder;
import scau.com.lprapm.entity.LogisticsInfo;
import scau.com.lprapm.entity.Orders;

import java.util.List;

/**
 * Created by 62353 on 2017/3/14.
 */
public interface DistributionOrderService {
    List<DistributionOrder> getBaseValue();
    int deleteRecordById(int order_id);
    int updateOrderStatus(int order_id);
    int getIfExitRecord(int order_id);
    int insertNewRecord(LogisticsInfo logisticsInfo);
    int updateRecordInfo(LogisticsInfo logisticsInfo);
}
