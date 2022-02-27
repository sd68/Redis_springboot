package com.dong.service;

import com.dong.pojo.User;

import java.util.List;

public interface UserService {
  Object getUserById(int id);
  List<User> getAllUser();
}
