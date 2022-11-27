package com.example.jingdong.service;

import com.example.jingdong.form.PasswordForm;
import com.example.jingdong.form.UserForm;
import com.example.jingdong.form.UserRegisterForm;
import com.example.jingdong.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    //保存用户至数据库
    User save(User user);

    //查找单个用户
    User findOne(Integer id);

    //根据openId查找用户
    User findByOpenId(String openId);

    //查找所有用户
    Page<User> findAll(Pageable pageable);

    //注册
    User register(UserRegisterForm userRegisterForm);

    //登录
    User login(String username ,String password);

    //新用户微信授权登录应先设置用户名
    User setUsername(Integer id, String username);

    //账号登录用户绑定微信 headPortrait为获取到的用户微信头像链接
    User bindWechatAccount(Integer id, String openId, String headPortrait);

    //更新用户信息
    User updateUserInfo(UserForm userForm);

    //更新密码
    User updatePassword(PasswordForm passwordForm);

}
