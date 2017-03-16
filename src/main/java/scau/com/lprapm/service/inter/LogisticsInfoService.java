package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.LogisticsInfo;

import java.util.List;

/**
 * Created by 62353 on 2017/3/13.
 */
public interface LogisticsInfoService {
    LogisticsInfo getInfoByCourierNum(String courier_number);

    List<LogisticsInfo> getAllRecordsByEmployeeId(int employee_id);

    int updateOrderInfo(int logisticsInfor_id,String order_info);
    int deleteRecordById(int logisticsInfor_id);

    int getRole(int userId);

    List<LogisticsInfo> getAllRecordsByUserId(int user_id);
}
