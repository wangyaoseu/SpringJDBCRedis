package com.springmvc.model;

/**
 * Created by wangyao on 2017/6/23.
 */
public class User {
    private Integer uid;
    private String username;

    public User() {}

    public User(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username  +
                '}';
    }
}
