package com.provider.demo.service;

import com.common.pojo.User;

import java.util.List;


public interface IUserService {

    int addUser(User user);

    List<User> selectList(User user);

    int updateUser(User user);

    int deleteUserById(int uid);
}
