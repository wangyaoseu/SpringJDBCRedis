package com.springmvc.service;

import com.springmvc.model.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by wangyao on 2017/6/26.
 */
public interface UserService {
    User getProfile(int uid);
}
