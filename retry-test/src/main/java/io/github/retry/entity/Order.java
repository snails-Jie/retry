package io.github.retry.entity;

/**
 * @ClassName Order
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/12/24 17:07
 **/
public class Order {
    private String orderNumber;
    private String orderUser;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }
}
