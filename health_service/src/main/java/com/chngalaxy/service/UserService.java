package com.chngalaxy.service;

import com.chngalaxy.pojo.User;

public interface UserService {
    User findByUsername(String username);
}
