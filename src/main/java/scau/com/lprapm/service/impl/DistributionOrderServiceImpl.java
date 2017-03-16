package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.DistributionOrderMapper;
import scau.com.lprapm.entity.DistributionOrder;
import scau.com.lprapm.entity.LogisticsInfo;
import scau.com.lprapm.entity.Orders;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.service.inter.DistributionOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 62353 on 2017/3/14.
 */
@Service
public class DistributionOrderServiceImpl implements DistributionOrderService {

    @Autowired
    DistributionOrderMapper distributionOrderMapper;

    @Override
    public List<DistributionOrder> getBaseValue() {
        List<Orders> orders = distributionOrderMapper.getBaseValue();
        List<DistributionOrder> distributionOrderList = new ArrayList<>();
        for (Orders order:orders) {
            DistributionOrder distributionOrder = new DistributionOrder();
            int orderId = order.getOrderId();
            int userId = order.getUserId();
            String orderCurrentStatus = order.getOrder_current_status();
            distributionOrder.setOrder_current_status(orderCurrentStatus);
            distributionOrder.setOrderId(orderId);
            distributionOrder.setUserId(userId);
            LogisticsInfo logisticsInfo = distributionOrderMapper.getLogInfo(orderId);
            System.out.println(logisticsInfo);
            if(logisticsInfo!=null){
                System.out.println(logisticsInfo.getOrder_price());
                distributionOrder.setOrder_price(logisticsInfo.getOrder_price());
                String employeeName = distributionOrderMapper.getEmployeeNameById(logisticsInfo.getEmployee_id());
                System.out.println(employeeName);
                distributionOrder.setEmployeeName(employeeName);
            }
            distributionOrderList.add(distributionOrder);
        }
        return distributionOrderList;
    }

    @Override
    public int deleteRecordById(int order_id) {
        return distributionOrderMapper.deleteRecordById(order_id);
    }

    @Override
    public int updateOrderStatus(int order_id) {
        return distributionOrderMapper.updateOrderStatus(order_id);
    }

    @Override
    public int getIfExitRecord(int order_id) {
        return distributionOrderMapper.getIfExitRecord(order_id);
    }

    @Override
    public int insertNewRecord(LogisticsInfo logisticsInfo) {
        return distributionOrderMapper.insertNewRecord(logisticsInfo);
    }

    @Override
    public int updateRecordInfo(LogisticsInfo logisticsInfo) {
        return distributionOrderMapper.updateRecordInfo(logisticsInfo);
    }
}
