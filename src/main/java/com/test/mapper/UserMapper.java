package com.test.mapper;

import com.test.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User login(User user);
    Set<String> findRoles(String username);
    Set<String> findPermission(String username);

    int insertRole(@Param("userid") Integer userid,@Param("roleid") Integer roleid);

}