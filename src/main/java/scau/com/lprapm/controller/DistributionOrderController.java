package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.DistributionOrder;
import scau.com.lprapm.entity.LogisticsInfo;
import scau.com.lprapm.service.inter.DistributionOrderService;

import java.util.List;
import java.util.Map;

/**
 * Created by 62353 on 2017/3/14.
 */

@Controller
@RequestMapping("/distributionOrder")
public class DistributionOrderController extends BaseController {
    @Autowired
    DistributionOrderService distributionOrderService;

    @ResponseBody
    @RequestMapping("searchOrdersValue")
    public JsonResult searchOrdersValue(){
        JsonResult jsonResult=null;
        try {
            List<DistributionOrder> uList=distributionOrderService.getBaseValue();
            jsonResult=new JsonResult(true,"查询成功",uList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("deleteRecord")
    public JsonResult deleteRecord(){
        JsonResult jsonResult=null;
        try {
            Map<String, Object> map = super.getParamMap();
            int order_id = Integer.parseInt(map.get("orderId").toString());
            distributionOrderService.deleteRecordById(order_id);
            jsonResult=new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("editRecord")
    public JsonResult editRecord(){
        JsonResult jsonResult=null;
        try {
            Map<String, Object> map = super.getParamMap();
            int order_id = Integer.parseInt(map.get("orderId").toString());
            int employee_id = Integer.parseInt(map.get("employeeId").toString());
            String order_price = (String)map.get("order_price");
            int user_id = Integer.parseInt(map.get("userId").toString());
            distributionOrderService.updateOrderStatus(order_id);
            LogisticsInfo logisticsInfo = new LogisticsInfo();
            String courier_number = Long.toString( System.currentTimeMillis());
            logisticsInfo.setCourier_number(courier_number);
            logisticsInfo.setEmployee_id(employee_id);
            logisticsInfo.setOrder_id(order_id);

            if(order_price=="" || order_price.isEmpty() || order_price == null) {
                logisticsInfo.setOrder_price("15元");
            }else{
                logisticsInfo.setOrder_price(order_price);
            }

            logisticsInfo.setUser_id(user_id);

            int result = distributionOrderService.getIfExitRecord(order_id);
            if(result>0){
                distributionOrderService.updateRecordInfo(logisticsInfo);
            }else{
                distributionOrderService.insertNewRecord(logisticsInfo);
            }
            jsonResult=new JsonResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

}
