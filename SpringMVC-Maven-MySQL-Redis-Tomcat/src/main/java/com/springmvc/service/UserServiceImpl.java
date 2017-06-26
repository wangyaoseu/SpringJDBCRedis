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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    //@Cacheable可以标记在一个方法上，也可以标记在一个类上。当标记在一个方法上时表示该方法是支持缓存的，
    //当标记在一个类上时则表示该类所有的方法都是支持缓存的。对于一个支持缓存的方法，Spring会在其被调用后将其返回值缓存起来，
    // 以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，而不需要再次执行该方法。Spring在缓存方法的返回值时是以键值对进行缓存的，
    // 值就是方法的返回结果，至于键的话，Spring又支持两种策略，默认策略和自定义策略，需要注意的是当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的。

    //@Cacheable 当调用这个方法的时候，会从一个名叫 redis_users 的缓存中查询，
    // 如果没有，则执行实际的方法（即查询数据库），并将执行的结果存入缓存中，
    // 否则返回缓存中的对象。这里的缓存中的 key 就是参数 uid，value 是 方法的返回值：user对象。

    @Cacheable (value="UsersCache",key = "#uid")
    @Override
    public User getProfile(int uid) {
        User user = new User(uid);
        String uname = userMapper.selectUserName(uid);
        System.out.println("db");
        user.setUsername(uname);
        return user;
    }
    /*
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
    }*/
}
