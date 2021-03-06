package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.*;
import scau.com.lprapm.entity.*;
import scau.com.lprapm.service.inter.OrdersService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/5.
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ReceiptMapper receiptMapper;
    @Autowired
    OrderExamMapper orderExamMapper;
    @Autowired
    PurPriceMapper purPriceMapper;
    @Autowired
    LogPriceMapper logPriceMapper;

    @Override
    public void insertOrders(Orders orders, Goods goods, Receipt receipt, OrderExam orderExam, PurPrice purPrice, LogPrice logPrice) {
        goodsMapper.insertGoods(goods);
        orders.setGoodsId(goods.getGoodsId());
        receiptMapper.insertReceipt(receipt);
        orders.setReceiptId(receipt.getReceiptId());
        orderExamMapper.insertOrderExam(orderExam);
        orders.setOeId(orderExam.getOeId());
        purPriceMapper.insertPurPrice(purPrice);
        orders.setPurId(purPrice.getPurId());
        logPriceMapper.insertLogPrice(logPrice);
        orders.setLogId(logPrice.getLogId());
        orders.setIsAskPur("否");
        orders.setIsAskLog("否");
        orders.setIsSure("否");
        orders.setOrder_current_status("未处理");
        ordersMapper.insert(orders);
    }

    @Override
    public List<Map<String, Object>> searchOrders(Map<String, Object> params) {
        return ordersMapper.searchOrders(params);
    }

    @Override
    public void updateOrders(Orders orders, Goods goods, Receipt receipt) {
        ordersMapper.updateByPrimaryKey(orders);
        goodsMapper.updateByPrimaryKey(goods);
        receiptMapper.updateByPrimaryKey(receipt);
    }

    @Override
    public void deleteOrders(Orders orders) {
        goodsMapper.deleteByPrimaryKey(orders.getGoodsId());
        receiptMapper.deleteByPrimaryKey(orders.getReceiptId());
        orderExamMapper.deleteByPrimaryKey(orders.getOeId());
        purPriceMapper.deleteByPrimaryKey(orders.getPurId());
        logPriceMapper.deleteByPrimaryKey(orders.getLogId());
        ordersMapper.deleteByPrimaryKey(orders.getOrderId());
    }

    @Override
    public void askOrders(Map<String, Object> params) {
        ordersMapper.askOrders(params);
    }

    @Override
    public void revokePOP(Map<String, Object> params) {
        Orders orders = new Orders();
        orders.setOrderId(Integer.parseInt(params.get("orderId").toString()));
        orders.setIsAskPur("否");
        PurPrice purPrice = new PurPrice();
        purPrice.setPurId(Integer.parseInt(params.get("purId").toString()));
        purPrice.setPurDept(null);
        purPrice.setPurPerson(null);
        purPrice.setPurPrice(null);
        purPrice.setPurState("否");
        purPriceMapper.updateByPrimaryKey(purPrice);
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public void revokeLOP(Map<String, Object> params) {
        Orders orders = new Orders();
        LogPrice logPrice = new LogPrice();
        orders.setOrderId(Integer.parseInt(params.get("orderId").toString()));
        orders.setIsAskLog("否");
        logPrice.setLogId(Integer.parseInt(params.get("logId").toString()));
        logPrice.setLogDept(null);
        logPrice.setLogPerson(null);
        logPrice.setLogPrice(null);
        logPrice.setLogState("否");
        logPriceMapper.updateByPrimaryKey(logPrice);
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

}
