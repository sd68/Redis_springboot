package com.dong.service.impl;

import com.dong.pojo.User;
import com.dong.mapper.UserMapper;
import com.dong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Object getUserById(int id) {
        //先从缓存获取数据，如果有，则直接返回，如果没有，则查询数据库，设置到缓存
        String key = "user:" + id;
        Object user = redisTemplate.opsForValue().get(key);
        if (user == null) {
            synchronized (this.getClass()) {//不限制从缓存取数据，给访问数据库加同步
                 user = redisTemplate.opsForValue().get(key);
                if(user==null){//在等待时数据有可能已经写入缓存，在此查询，若还没有，再真正查询数据库
                    User sqlUser = userMapper.getUserById(id);
                    redisTemplate.opsForValue().set(key, sqlUser);
                    System.out.println("查数据库");
                    return sqlUser;
                }

            }

        }
        System.out.println("查缓存");
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }
}
