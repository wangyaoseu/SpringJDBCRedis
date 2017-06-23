package com.springmvc.mapper;
import com.springmvc.model.User;
/**
 * Created by wangyao on 2017/6/23.
 */

public interface UserMapper {
    int selectUserCount();
    void updateUser(User user);
    String selectUserName(Integer uid);
}
