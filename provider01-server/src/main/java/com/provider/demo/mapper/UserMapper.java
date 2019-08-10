package com.provider.demo.mapper;

import com.provider.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int addUser(User user);

    List<User> selectList(User user);

    int updateUser(User user);

    int deleteUserById(int uid);
}
