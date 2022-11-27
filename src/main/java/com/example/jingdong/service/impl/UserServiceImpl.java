package com.example.jingdong.service.impl;

import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.PasswordForm;
import com.example.jingdong.form.UserForm;
import com.example.jingdong.form.UserRegisterForm;
import com.example.jingdong.pojo.User;
import com.example.jingdong.repository.UserRepository;
import com.example.jingdong.service.UserService;
import com.example.jingdong.utils.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOne(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public User findByOpenId(String openId) {

        if (null == openId || "".equals(openId)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        return userRepository.findByOpenId(openId);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User register(UserRegisterForm userRegisterForm) {
        //验证重复密码是否一致
        if (!userRegisterForm.getPassword().equals(userRegisterForm.getRePassword())) {
            throw new SellException(ResultEnum.USER_REP_PASSWORD_NOT_MATCH);
        }

        //查询是否已注册
        User result = userRepository.findByUsername(userRegisterForm.getUsername());
        if (result != null) {
            throw new SellException(ResultEnum.USER_ALREADY_EXIST);
        }
        //保存信息
        User user = new User();
        user.setUsername(userRegisterForm.getUsername());
        //得到一个随机字符串作为用户编号
        String number = getNumber();
        //设置用户编号
        user.setNumber(number);

        //将编号转换为StringBuffer 翻转顺序 再转为String
        String reverseNumber = new StringBuffer(number).reverse().toString();
        //将密码拼接翻转后的编号 使用MD5加密
        user.setPassword(Md5Util.md5(userRegisterForm.getPassword() + reverseNumber));

        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }

        //校验密码
        //将用户编号转换为StringBuffer 翻转顺序 再转为String
        String reverseNumber = new StringBuffer(user.getNumber()).reverse().toString();
        //将密码拼接翻转后的编号 使用MD5加密
        String passwordMD5 = Md5Util.md5(password + reverseNumber);
        //校验密码是否正确
        if (!passwordMD5.equals(user.getPassword())) {
            throw new SellException(ResultEnum.USER_PASSWORD_ERROR);
        }

        return user;
    }

    @Override
    public User setUsername(Integer id, String username) {
        if (null == id || null == username){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if (username.length() < 6 || username.length() > 18){
            throw new SellException(ResultEnum.USERNAME_LENGTH_ERROR);
        }
        //查询用户名是否已存在
        User byUsername = userRepository.findByUsername(username);
        if (byUsername != null){
            throw new SellException(ResultEnum.USER_ALREADY_EXIST);
        }
        //查询要设置用户名的用户是否存在
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()){
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }
        
        User user = byId.get();
        //校验该账号是否已设置用户名
        if (null != user.getUsername()){
            throw new SellException(ResultEnum.ACCOUNT_HAS_A_USERNAME);
        }
        
        user.setUsername(username);

        return userRepository.save(user);
    }

    @Override
    public User bindWechatAccount(Integer id, String openId, String headPortrait) {
        //根据openId查找用户 如果不为空说明已被绑定
        User byOpenId = userRepository.findByOpenId(openId);
        if (null != byOpenId){
            throw new SellException(ResultEnum.WECHAT_HAS_BEEN_BOUND);
        }

        User user = findOne(id);
        if (null == user){
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }
        if (null != user.getOpenId()){
            throw new SellException(ResultEnum.USER_HAS_BEEN_BOUND);
        }

        //校验完毕 保存
        user.setOpenId(openId);
        user.setHeadPortrait(headPortrait);

        return save(user);
    }

    @Override
    public User updateUserInfo(UserForm userForm) {
        //查询用户是否存在
        Optional<User> optionalUser = userRepository.findById(userForm.getId());
        if (!optionalUser.isPresent()) {
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }

        User oldUser = optionalUser.get();
        //将提交的信息赋值给原对象
        BeanUtils.copyProperties(userForm, oldUser);

        //执行更新操作
        return userRepository.save(oldUser);
    }

    @Override
    public User updatePassword(PasswordForm passwordForm) {
        //校验重复密码是否一致
        if (!passwordForm.getPassword().equals(passwordForm.getRePassword())) {
            throw new SellException(ResultEnum.REPEAT_PASSWORD_ERROR);
        }
        //查询用户是否存在
        Optional<User> optionalUser = userRepository.findById(passwordForm.getId());
        if (!optionalUser.isPresent()) {
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }
        User oldUser = optionalUser.get();

        /*
        *校验旧密码是否正确
        *密码加密规则： 将用户唯一编号number翻转顺序后拼接密码进行md5加盐加密
        *密码在前 编号在后   MD5( password + number )
        * */

        //先判断用户是否有密码字段 以及密码加盐的number字段
        //微信授权登录的用户初始没有密码 以及number字段 故不需要比较旧密码
        //直接获取随机number 并加密设置新密码
        if (null == oldUser.getNumber() && null == oldUser.getPassword()){
            String number = getNumber();
            String reverseNumber = new StringBuffer(number).reverse().toString();
            String newPassword = Md5Util.md5(passwordForm.getPassword() + reverseNumber);
            oldUser.setPassword(newPassword);
            oldUser.setNumber(number);
        } else {
            //数据中有密码及number字段
            //校验旧密码是否正确
            String reverseNumber = new StringBuffer(oldUser.getNumber()).reverse().toString();
            String md5Password = Md5Util.md5(passwordForm.getOldPassword() + reverseNumber);

            if (!md5Password.equals(oldUser.getPassword())) {
                throw new SellException(ResultEnum.OLD_PASSWORD_ERROR);
            }

            //参数及旧密码校验完毕 设置新密码
            String newPassword = Md5Util.md5(passwordForm.getPassword() + reverseNumber);
            oldUser.setPassword(newPassword);
        }

        //执行更新操作
        return userRepository.save(oldUser);
    }


    //随机获取用户id编号
    private synchronized String getNumber() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return String.valueOf(System.currentTimeMillis()).substring(0, 6) + number;
    }


}
