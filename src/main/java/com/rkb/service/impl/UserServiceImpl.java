package com.rkb.service.impl;

import com.rkb.dao.RandomDao;
import com.rkb.dao.UserDao;
import com.rkb.pojo.User;
import com.rkb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RandomDao randomDao;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void regist(User user) {
        // TODO Auto-generated method stub
            Integer id = randomDao.findCore(user.getCore());
            randomDao.deleteCore(id);
            userDao.addUser(user);
    }

    @Override
    public User login(String name, String password) {
        // TODO Auto-generated method stub
        return userDao.findUserByNameAndPwd(name,password);
    }

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		return userDao.findUserById(id);
	}

    @Override
    public Boolean assertCore(String core) {
        Integer id = randomDao.findCore(core);
        if (id==null)
            return false;
        else
            return true;
    }

    @Override
    public Boolean forgotPw(User user) {
        User oldUser = userDao.findUserByName(user.getName());
        if (oldUser!=null&&oldUser.getCore().equals(user.getCore())){
            userDao.updateUser(user);
            return true;
        }
        else
            return false;
    }
    @Override
    public User findUserByName(String name){
        return userDao.findUserByName(name);
    }

}