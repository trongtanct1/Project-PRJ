/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author ACER
 */
public class Product {

    private String pro_id;
    private String pro_name;
    private int pro_quan;
    private long pro_price;
    private String pro_pic;
    private String pro_des;

    public Product() {
    }

    public Product(String pro_id, String pro_name, int pro_quan, long pro_price, String pro_pic, String pro_des) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_quan = pro_quan;
        this.pro_price = pro_price;
        this.pro_pic = pro_pic;
        this.pro_des = pro_des;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getPro_quan() {
        return pro_quan;
    }

    public void setPro_quan(int pro_quan) {
        this.pro_quan = pro_quan;
    }

    public long getPro_price() {
        return pro_price;
    }

    public void setPro_price(long pro_price) {
        this.pro_price = pro_price;
    }

    public String getPro_pic() {
        return pro_pic;
    }

    public void setPro_pic(String pro_pic) {
        this.pro_pic = pro_pic;
    }

    public String getPro_des() {
        return pro_des;
    }

    public void setPro_des(String pro_des) {
        this.pro_des = pro_des;
    }

}
