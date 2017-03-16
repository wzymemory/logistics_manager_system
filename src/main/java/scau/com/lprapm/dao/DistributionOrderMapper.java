package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Action;
import scau.com.lprapm.entity.LogisticsInfo;
import scau.com.lprapm.entity.Orders;
import scau.com.lprapm.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface DistributionOrderMapper {
    List<Orders> getBaseValue();
    LogisticsInfo getLogInfo(int order_id);
    String getEmployeeNameById(int user_id);
    int deleteRecordById(int order_id);
    int updateOrderStatus(int order_id);
    int getIfExitRecord(int order_id);
    int insertNewRecord(LogisticsInfo logisticsInfo);
    int updateRecordInfo(LogisticsInfo logisticsInfo);
}