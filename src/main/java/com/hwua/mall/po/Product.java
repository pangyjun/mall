package com.hwua.mall.po;

import javax.xml.crypto.Data;

public class Product {

    private Integer id;
    private String name;
    private String comm;
    private Double price;
    private Integer stock;
    private Integer status;
    private Integer useable;
    private Data date;
    private String imgs;
    private Double pre_price;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comm='" + comm + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", status=" + status +
                ", useable=" + useable +
                ", date=" + date +
                ", imgs='" + imgs + '\'' +
                ", pre_price=" + pre_price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUseable() {
        return useable;
    }

    public void setUseable(Integer useable) {
        this.useable = useable;
    }

    public Data getDate() {
        return date;
    }

    public void setDate(Data date) {
        this.date = date;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Double getPre_price() {
        return pre_price;
    }

    public void setPre_price(Double pre_price) {
        this.pre_price = pre_price;
    }
}
