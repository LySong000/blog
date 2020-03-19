package com.lys.blog.service;

import com.lys.blog.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
