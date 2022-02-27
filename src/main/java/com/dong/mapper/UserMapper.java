package com.dong.mapper;

import com.dong.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
     User getUserById(int id);
     List<User> getAllUser();


}
