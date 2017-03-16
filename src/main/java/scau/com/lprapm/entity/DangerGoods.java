package scau.com.lprapm.entity;

/**
 * Created by 62353 on 2017/3/13.
 */
public class DangerGoods {
    private int dangerGoods_id;
    private String dangerGoods_type;
    private String dangerGoods_name;
    private String transportationByLand;
    private String transportationByAir;
    private String userNeedToKnow;

    public DangerGoods() {
        super();
    }

    public DangerGoods(int dangerGoods_id, String dangerGoods_type, String dangerGoods_name, String transportationByLand, String transportationByAir, String userNeedToKnow) {
        this.dangerGoods_id = dangerGoods_id;
        this.dangerGoods_type = dangerGoods_type;
        this.dangerGoods_name = dangerGoods_name;
        this.transportationByLand = transportationByLand;
        this.transportationByAir = transportationByAir;
        this.userNeedToKnow = userNeedToKnow;
    }

    public int getDangerGoods_id() {
        return dangerGoods_id;
    }

    public void setDangerGoods_id(int dangerGoods_id) {
        this.dangerGoods_id = dangerGoods_id;
    }

    public String getDangerGoods_type() {
        return dangerGoods_type;
    }

    public void setDangerGoods_type(String dangerGoods_type) {
        this.dangerGoods_type = dangerGoods_type;
    }

    public String getDangerGoods_name() {
        return dangerGoods_name;
    }

    public void setDangerGoods_name(String dangerGoods_name) {
        this.dangerGoods_name = dangerGoods_name;
    }

    public String getTransportationByLand() {
        return transportationByLand;
    }

    public void setTransportationByLand(String transportationByLand) {
        this.transportationByLand = transportationByLand;
    }

    public String getTransportationByAir() {
        return transportationByAir;
    }

    public void setTransportationByAir(String transportationByAir) {
        this.transportationByAir = transportationByAir;
    }

    public String getUserNeedToKnow() {
        return userNeedToKnow;
    }

    public void setUserNeedToKnow(String userNeedToKnow) {
        this.userNeedToKnow = userNeedToKnow;
    }
}
