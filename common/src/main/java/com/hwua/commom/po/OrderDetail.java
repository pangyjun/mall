package com.hwua.commom.po;


public class OrderDetail {

    private Integer id;
    private Integer pid;
    private Integer count;
    private Double price;
    private Integer oid;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", pid=" + pid +
                ", count=" + count +
                ", price=" + price +
                ", oid=" + oid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
}
