package com.oracle.hrb.err.serverlt;

import com.oracle.hrb.err.UserEum.UserEnum;
import com.oracle.hrb.err.bean.User;
import com.oracle.hrb.err.dao.UserDao;
import com.oracle.hrb.err.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiset {
    @Autowired
    private UserDao dao;
    public UserEnum one(User user) {
        User u = dao.findByName(user.getName());
        if (user.getName() == null || user.getName().trim().length() == 0) {
            return UserEnum.USERNAME_NULL;
        }
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return UserEnum.PASSWORD_NULL;
        }
        user.setPassword(Md5Util.sha256(Md5Util.sha256(user.getPassword())));
        if (u == null ||!u.getPassword().equals(user.getPassword())){
            return UserEnum.USERWORD_PASSWORD_EX;
        }
        return UserEnum.OK;
    }
    public UserEnum addzhuce(User user){
        User u = dao.findByName(user.getName());
        if (user.getName() == null || user.getName().trim().length() == 0) {
            return UserEnum.USERNAME_NULL;
        }
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return UserEnum.PASSWORD_NULL;
        }
        if (user.getNickname() == null || user.getNickname().trim().length() == 0) {
            return UserEnum.USERNAMEOO_ER;
        }
        if (u!= null) {
            return UserEnum.USERWORD_REPEAT;
        }
        user.setId(UUID.randomUUID().toString());
        user.setPassword(Md5Util.sha256(Md5Util.sha256(user.getPassword())));
        dao.add(user);
        return UserEnum.OK;
    }
    public UserEnum updatezhuce(User user){
        User updateuser=dao.findById(user.getId());
        System.out.println(updateuser);
        user.setPassword(Md5Util.sha256(Md5Util.sha256(user.getPassword())));
        updateuser.setPassword(user.getPassword());
        dao.update(updateuser);
        return UserEnum.OK;
    }
}
