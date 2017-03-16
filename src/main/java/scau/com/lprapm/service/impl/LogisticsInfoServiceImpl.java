package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.LogisticsInfoMapper;
import scau.com.lprapm.entity.LogisticsInfo;
import scau.com.lprapm.service.inter.LogisticsInfoService;

import java.util.List;

/**
 * Created by 62353 on 2017/3/13.
 */
@Service
public class LogisticsInfoServiceImpl implements LogisticsInfoService {
    @Autowired
    LogisticsInfoMapper logisticsInfoMapper;
    @Override
    public LogisticsInfo getInfoByCourierNum(String courier_number) {
        return logisticsInfoMapper.getInfoByCourierNum(courier_number);
    }

    @Override
    public List<LogisticsInfo> getAllRecordsByEmployeeId(int employee_id) {
        return logisticsInfoMapper.getAllRecordsByEmployeeId(employee_id);
    }

    @Override
    public int updateOrderInfo(int logisticsInfor_id, String order_info) {
        return logisticsInfoMapper.updateOrderInfo(logisticsInfor_id,order_info);
    }

    @Override
    public int deleteRecordById(int logisticsInfor_id) {
        return logisticsInfoMapper.deleteRecordById(logisticsInfor_id);
    }

    @Override
    public int getRole(int userId) {
        return logisticsInfoMapper.getRole(userId);
    }

    @Override
    public List<LogisticsInfo> getAllRecordsByUserId(int user_id) {
        return logisticsInfoMapper.getAllRecordsByUserId(user_id);
    }
}
