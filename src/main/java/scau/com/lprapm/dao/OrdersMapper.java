package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Orders;

import java.util.List;
import java.util.Map;

@Repository
public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    /**
     * -----------------------分割线---------------------------
     **/
    @Select("<script>" +
            "select order_id orderId, o.goods_id goodsId, " +
            " o.receipt_id receiptId, user_id userId, o.pur_id purId, o.log_id logId," +
            " user_name userName, is_pur isPur,is_ask_pur isAskPur, " +
            " is_ask_log isAskLog,create_time createTime, end_time endTime, " +
            " order_address orderAddress, order_current_status order_current_status, goods_name goodsName, " +
            " goods_number goodsNumber, goods_volume goodsVolume, " +
            " goods_perweight goodsPerweight ," +
            " receipt_name receiptName, receipt_phone receiptPhone, " +
            " receipt_address receiptAddress, receipt_state receiptState, " +
            " receipt_time receiptTime, pur_dept purDept, pur_person purPerson," +
            " pur_price purPrice, pur_state purState,log_dept logDept," +
            " log_person logPerson,log_price logPrice,log_state logState " +
            " from orders o,goods g,receipt r,pur_price pp,log_price lp " +
            " where o.goods_id=g.goods_id and " +
            " o.receipt_id=r.receipt_id and o.pur_id=pp.pur_id and o.log_id=lp.log_id " +
            " <if test=\"user_id != null and user_id != '' \">" +
            "     and user_id=#{userId}" +
            " </if>" +
            " <if test=\"order_id != null and order_id != '' \">" +
            "     and order_id=#{order_id}" +
            " </if>" +
            " <if test=\"createTime != null and createTime != '' \">" +
            "    and <![CDATA[create_time >= #{createTime}]]>" +
            " </if>" +
            " <if test=\"endTime != null and endTime != '' \">" +
            "    and <![CDATA[create_time <= #{endTime}]]>" +
            " </if>" +
            " <if test=\"goodsName != null and goodsName != '' \">" +
            "    and goods_name like concat('%',#{goodsName},'%')" +
            " </if>" +
            " <if test=\"receiptName != null and receiptName != '' \">" +
            "    and receipt_name like concat('%',#{receiptName},'%')" +
            " </if>" +
            " <if test=\"isAskPur != null and isAskPur != '' \">" +
            "    and is_ask_pur like concat('%',#{isAskPur},'%')" +
            " </if>" +
            " <if test=\"isAskLog != null and isAskLog != '' \">" +
            "    and is_ask_log like concat('%',#{isAskLog},'%')" +
            " </if>" +
            " <if test=\"purState != null and purState != '' \">" +
            "    and pur_state = #{purState}" +
            " </if>" +
            " <if test=\"isPur != null and isPur != '' \">" +
            "    and is_pur = #{isPur}" +
            " </if>"+
            "</script>")
    List<Map<String, Object>> searchOrders(Map<String, Object> params);

    @Update("<script>" +
            "update orders " +
            "set " +
            " <if test=\"isAskPur != null and isAskPur != '' \">" +
            "    is_ask_pur = #{isAskPur,jdbcType=VARCHAR} " +
            " </if>" +
            " <if test=\"isAskLog != null and isAskLog != '' \">" +
            "    is_ask_log = #{isAskLog,jdbcType=VARCHAR} " +
            " </if>"+
            "where order_id = #{orderId,jdbcType=INTEGER}" +
            "</script>")
    void askOrders(Map<String, Object> params);
}