package com.jinqi.dao;

import com.jinqi.domain.User;
import com.jinqi.mybatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface UserDao {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")//注解直接写在dao接口上
    List<User> findAll();
}
