package com.oracle.vo;

/**
 * @author Clarinet
 * @since JDK8
 * ORM:对应数据库中的Didi表
 */
public class Didi {
    private String id;//UUID,主键,唯一标识
    private String phoneNumber;//手机号
    private String address;//地址
    private String time;//打车时间
    private int distance;//打车距离
    private int price;//本次打车的价格
    private int orderCount;//订单数量汇总
    private int priceCount;//价格汇总

    public Didi(){

    }

    @Override
    public String toString() {
        return "Didi{" +
                "id='" + id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", time='" + time + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                ", orderCount=" + orderCount +
                ", priceCount=" + priceCount +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getPriceCount() {
        return priceCount;
    }

    public void setPriceCount(int priceCount) {
        this.priceCount = priceCount;
    }
}
