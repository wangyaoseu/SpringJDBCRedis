package com.springmvc.service;
import com.springmvc.mapper.UserMapper;
import com.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Created by wangyao on 2017/6/23.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisPool jedisPool;

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Cacheable   //查一下使用方法
    public User getProfile( int uid) {
        //从数据库得到User对象
        User user = new User(uid);
        //设置获赞数，关注数，粉丝数
        Jedis jedis = jedisPool.getResource();

        String uname = jedis.get(uid+"");
        if(uname==null){
            uname = userMapper.selectUserName(uid);
            user.setUsername(uname);
            jedis.set(String.valueOf(uid), uname);
            System.out.println("db"+uname);
        }
        else{
            user.setUsername(uname);
            System.out.println("redis"+uname);
        }

        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return user;
    }
}
