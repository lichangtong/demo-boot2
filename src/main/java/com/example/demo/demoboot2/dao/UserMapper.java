package com.example.demo.demoboot2.dao;

import com.example.demo.demoboot2.para.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-14 10:32
 * @Description:
 */
@Repository
public interface UserMapper {
    int addUser(User user);

    int delUser(User user);

    int updateUser(User user);

    List<User> queryUser(User user);

    List<User> queryAllUser();
}
