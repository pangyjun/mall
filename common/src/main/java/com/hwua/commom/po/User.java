package com.hwua.commom.po;

public class User {
    private Integer dbid;
    private String userName;
    private String password;
    private String valid;

    @Override
    public String toString() {
        return "User{" +
                "dbid=" + dbid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
