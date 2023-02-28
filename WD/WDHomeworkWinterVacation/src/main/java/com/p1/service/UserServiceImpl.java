package com.p1.service;


import com.p1.exception.user.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.p1.dao.UserMapper;
import com.p1.pojo.User;
import com.p1.pojo.UserExample;
import com.p1.service.inter.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }


    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    /**
     * username：用户名
     *
     * 查询前检查用户是否存在
     * */
    @Override
    public User selectUserByName(String username) throws UserNotFound {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        User user=(userMapper.selectByExample(userExample)).get(0);
        if(user==null){
            throw new UserNotFound(username);
        }
        return user;
    }

    /**
     * username:用户名
     * password:密码
     * checkPassword：再次确认密码
     *
     * 注册确认，checkPassword与password不一样则返回false，一样返回true并持久化数据
     * */
    @Override
    public boolean registerCheck(String username, String password, String checkPassword) {
        if (checkPassword.equals(password)){
            User user=new User();
            user.setUserPassword(password);
            user.setUserName(username);
            userMapper.insert(user);    //数据持久化
            return true;
        }else {
            return false;
        }
    }

    /**
     * username:用户id
     * password:密码
     *
     * 确认密码
     * */
    @Override
    public boolean loginCheck(String username,String password){
        UserExample userExample=new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        String localPassword=userMapper.selectByExample(userExample).get(0).getUserPassword();
        return localPassword.equals(password);
    }
}
