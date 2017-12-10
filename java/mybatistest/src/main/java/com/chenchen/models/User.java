package com.chenchen.models;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String name;
    private String dept;
    private String phone;
    private String website;
    //private Sex sex;
    private Sex sex2;

    public User() {
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", sex2=" + sex2 +
                '}';
    }

    public Sex getSex2() {
        return sex2;
    }

    public void setSex2(Sex sex2) {
        this.sex2 = sex2;
    }
}