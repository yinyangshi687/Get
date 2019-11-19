package com.oracle.hrb.err.dao;

import com.oracle.hrb.err.bean.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void delete(String id);
    void update(User user);
    List<User> findAll();
    User findByName(String name);
    User findById(String id);
}
