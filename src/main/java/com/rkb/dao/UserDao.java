package com.rkb.dao;


import com.rkb.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 持久层映射接口
 * @author Nocol
 *
 */
@Repository
public interface UserDao {

    //添加用户
    public void addUser(User user);

    //根据用户名和密码查询用户
    //注解的两个参数会自动封装成map集合，括号内即为键
    User findUserByNameAndPwd(@Param("name") String name, @Param("password") String password);

	User findUserById(Long id);
	User findUserByName(String name);
	void updateUser(User user);
}
