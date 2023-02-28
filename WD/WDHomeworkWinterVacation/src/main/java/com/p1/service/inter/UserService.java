package com.p1.service.inter;

import com.p1.pojo.User;

public interface UserService {

    int insertUser(User user);
    User selectUserByName(String name);
    boolean registerCheck(String username, String password,String checkPassword);
    boolean loginCheck(String username,String password);

}
