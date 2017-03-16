package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.DangerGoods;
import scau.com.lprapm.entity.LogisticsInfo;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.service.inter.LogisticsInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 62353 on 2017/3/13.
 */
@Controller
@RequestMapping("/logisticsInfo")
public class LogisticsInfoController extends BaseController {
    @Autowired
    private LogisticsInfoService logisticsInfoService;
    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping("orderTrack")
    public JsonResult orderTrack(@RequestParam("courier_number") String courier_number){
        JsonResult jsonResult=null;
        try {
            LogisticsInfo logisticsInfo = logisticsInfoService.getInfoByCourierNum(courier_number);
            jsonResult=new JsonResult(true,"查询物流信息成功",logisticsInfo);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询物流信息失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("searchLogInfoValue")
    public JsonResult searchLogInfoValue(){
        JsonResult jsonResult=null;
        try {
            User user =  (User)request.getSession(true).getAttribute(Constant.CURRENR_USER);
            int userId = user.getUserId();
            int roleId = logisticsInfoService.getRole(userId);
            List<LogisticsInfo> lists;
            if(roleId!=1){
                lists = logisticsInfoService.getAllRecordsByEmployeeId(userId);
            }else{
                lists  = logisticsInfoService.getAllRecordsByUserId(userId);
            }

            jsonResult=new JsonResult(true,"查询成功",lists);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("editLogInfo")
    public JsonResult editLogInfo(){
        JsonResult jsonResult=null;
        try {
            Map map = super.getParamMap();
            int logisticsInfor_id = Integer.parseInt(map.get("logisticsInfor_id").toString());
            String order_info = map.get("order_info").toString();
            int result = logisticsInfoService.updateOrderInfo(logisticsInfor_id,order_info);
            if(result>0){
                jsonResult=new JsonResult(true,"修改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"修改失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("deleteRecord")
    public JsonResult deleteRecord(){
        JsonResult jsonResult=null;
        try {
            Map map = super.getParamMap();
            int logisticsInfor_id = Integer.parseInt(map.get("logisticsInfor_id").toString());
            int result = logisticsInfoService.deleteRecordById(logisticsInfor_id);
            if(result>0){
                jsonResult=new JsonResult(true,"删除成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"删除失败");
        }
        return jsonResult;
    }

}
