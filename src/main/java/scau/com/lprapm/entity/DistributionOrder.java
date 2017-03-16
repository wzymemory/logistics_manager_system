package scau.com.lprapm.entity;

import java.util.List;
import java.util.Map;

public class DistributionOrder {
    private Integer orderId;
    private Integer userId;
    private String order_current_status;
    private String employeeName;
    private String order_price;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrder_current_status() {
        return order_current_status;
    }

    public void setOrder_current_status(String order_current_status) {
        this.order_current_status = order_current_status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }
}