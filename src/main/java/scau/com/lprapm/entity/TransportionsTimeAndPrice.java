package scau.com.lprapm.entity;

/**
 * Created by 62353 on 2017/3/13.
 */
public class TransportionsTimeAndPrice {
    private String productName;
    private int weightNum;
    private String needTime;
    private String needPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getWeightNum() {
        return weightNum;
    }

    public void setWeightNum(int weightNum) {
        this.weightNum = weightNum;
    }

    public String getNeedTime() {
        return needTime;
    }

    public void setNeedTime(String needTime) {
        this.needTime = needTime;
    }

    public String getNeedPrice() {
        return needPrice;
    }

    public void setNeedPrice(String needPrice) {
        this.needPrice = needPrice;
    }
}
