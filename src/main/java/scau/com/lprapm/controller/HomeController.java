package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.DangerGoods;
import scau.com.lprapm.entity.TransportionsTimeAndPrice;
import scau.com.lprapm.service.inter.HomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 62353 on 2017/3/13.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HomeService homeService;

    @ResponseBody
    @RequestMapping("selectRule")
    public JsonResult selectRule(@RequestParam("dangerGoodsName") String dangerGoodsName){
        JsonResult jsonResult=null;
        try {
            List<DangerGoods> dangerGoods = homeService.getDangerGoodsByName(dangerGoodsName);
            int total = dangerGoods.size();
            jsonResult=new JsonResult(total,true,"查询违禁物品成功",dangerGoods);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询违禁物品失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("selectTime")
    public JsonResult selectTime(@RequestParam("fromAddress") String fromAddress,@RequestParam("toAddress") String toAddress,@RequestParam("goodWeight") int goodWeight){
        JsonResult jsonResult=null;
        TransportionsTimeAndPrice transportionsTimeAndPrice = new TransportionsTimeAndPrice();
        try {
            if(!fromAddress.equals("") && !toAddress.equals("")){
                String fromProvince = fromAddress.split("/")[0];
                String toProvince = toAddress.split("/")[0];
                if(fromProvince.equals(toProvince)){
                    transportionsTimeAndPrice.setNeedTime("1天");
                    transportionsTimeAndPrice.setProductName("当日物流");
                    transportionsTimeAndPrice.setWeightNum(goodWeight);
                }else{
                    transportionsTimeAndPrice.setNeedTime("1~2天");
                    transportionsTimeAndPrice.setProductName("隔日物流");
                    transportionsTimeAndPrice.setWeightNum(goodWeight);
                }

                if(goodWeight<=5){
                    transportionsTimeAndPrice.setNeedPrice("15元");
                }
                if(goodWeight>5 && goodWeight<=10){
                    int currentPrice = (goodWeight-5)*2+15;
                    String price = currentPrice +"元";
                    transportionsTimeAndPrice.setNeedPrice(price);

                }
                if(goodWeight>10){
                    int currentPrice = (goodWeight-5)*3+15;
                    String price = currentPrice+"元";
                    transportionsTimeAndPrice.setNeedPrice(price);
                }
                jsonResult=new JsonResult(true,"查询时长成功",transportionsTimeAndPrice);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询时长失败");
        }
        return jsonResult;
    }
}
