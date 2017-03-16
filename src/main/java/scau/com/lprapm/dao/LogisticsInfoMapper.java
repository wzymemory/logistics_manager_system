package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.LogisticsInfo;

import java.util.List;


@Repository
public interface LogisticsInfoMapper {
    LogisticsInfo getInfoByCourierNum(String courier_number);
    List<LogisticsInfo> getAllRecordsByEmployeeId(int employee_id);
    List<LogisticsInfo> getAllRecordsByUserId(int user_id);
    int updateOrderInfo(@Param("logisticsInfor_id") int logisticsInfor_id, @Param("order_info") String order_info);
    int deleteRecordById(int logisticsInfor_id);
    int getRole(int user_id);
}