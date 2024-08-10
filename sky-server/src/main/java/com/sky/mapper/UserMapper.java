package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * Query user information based on openId
     * @param openId
     * @return User
     */
    @Select("select * from user where openid = #{openId}")
    User getByOpenId(String openId);

    /**
     * Insert user information
     * @param user
     */
    void insert(User user);

    /**
     * Query user information based on userId
     * @param userId
     * @return User
     */
    @Select("select * from user where id = #{userId}")
    User getById(Long userId);
}
