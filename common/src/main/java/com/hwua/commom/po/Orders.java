package com.hwua.commom.po;

import java.util.Date;

public class Orders {
    private Integer id;
    private Integer mid;
    private Integer aid;
    private Double tot_money;
    private Integer flag;
    private Date order_time;
    private Date pay_time;
    private Date confirm_time;
    private Date send_time;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", mid=" + mid +
                ", aid=" + aid +
                ", tot_money=" + tot_money +
                ", flag=" + flag +
                ", order_time=" + order_time +
                ", pay_time=" + pay_time +
                ", confirm_time=" + confirm_time +
                ", send_time=" + send_time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Double getTot_money() {
        return tot_money;
    }

    public void setTot_money(Double tot_money) {
        this.tot_money = tot_money;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Date getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(Date confirm_time) {
        this.confirm_time = confirm_time;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }
}
