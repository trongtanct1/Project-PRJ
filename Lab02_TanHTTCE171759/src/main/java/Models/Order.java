/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author ACER
 */
public class Order {

    private String orderID;
    private String userName;
    private long orderTotal;
    private Date orderDate;
    private String orderDes;

    public Order() {
    }

    public Order(String orderID, String userName, long orderTotal, Date orderDate, String orderDes) {
        this.orderID = orderID;
        this.userName = userName;
        this.orderTotal = orderTotal;
        this.orderDate = orderDate;
        this.orderDes = orderDes;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(long orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDes() {
        return orderDes;
    }

    public void setOrderDes(String orderDes) {
        this.orderDes = orderDes;
    }
}
