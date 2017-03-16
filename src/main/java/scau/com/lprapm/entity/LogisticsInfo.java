package scau.com.lprapm.entity;

/**
 * Created by 62353 on 2017/3/13.
 */
public class LogisticsInfo {
    private int logisticsInfor_id;
    private int order_id;
    private String courier_number;
    private int user_id;
    private int employee_id;
    private String order_price;
    private String order_info;
    private int order_state;

    public LogisticsInfo() {
        super();
    }

    public LogisticsInfo(int logisticsInfor_id, int order_id, String courier_number, int user_id, int employee_id, String order_price, String order_info, int order_state) {
        this.logisticsInfor_id = logisticsInfor_id;
        this.order_id = order_id;
        this.courier_number = courier_number;
        this.user_id = user_id;
        this.employee_id = employee_id;
        this.order_price = order_price;
        this.order_info = order_info;
        this.order_state = order_state;
    }

    public int getLogisticsInfor_id() {
        return logisticsInfor_id;
    }

    public void setLogisticsInfor_id(int logisticsInfor_id) {
        this.logisticsInfor_id = logisticsInfor_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCourier_number() {
        return courier_number;
    }

    public void setCourier_number(String courier_number) {
        this.courier_number = courier_number;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_info() {
        return order_info;
    }

    public void setOrder_info(String order_info) {
        this.order_info = order_info;
    }

    public int getOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }
}
