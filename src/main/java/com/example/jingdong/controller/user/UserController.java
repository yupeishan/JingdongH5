package com.example.jingdong.controller.user;

import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.PasswordForm;
import com.example.jingdong.form.UserForm;
import com.example.jingdong.form.UserRegisterForm;
import com.example.jingdong.pojo.User;
import com.example.jingdong.service.UserService;
import com.example.jingdong.utils.ResultUtil;
import com.example.jingdong.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WxMpService wxMpService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //用户注册
    @PostMapping("register")
    public Result<UserVO> register(@Valid UserRegisterForm userRegisterForm,
                                   BindingResult bindingResult) {
        //校验form参数
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.USER_PARAMS_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        User user = userService.register(userRegisterForm);

        if (user == null) {
            throw new SellException(ResultEnum.USER_REGISTER_ERROR);
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return ResultUtil.success(userVO);
    }


    //用户登录
    @PostMapping("login")
    public Result<UserVO> login(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpServletResponse response) {

        if (password == null || username == null) {
            throw new SellException(ResultEnum.USER_PARAMS_ERROR);
        }

        User user = userService.login(username, password, response);
        if (user == null) {
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }

        //返回UserVO对象 去掉敏感信息
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return ResultUtil.success(userVO);
    }

    //用户微信授权登录接口
    @PostMapping("wechatLogin")
    public Result<UserVO> wechatLogin(@RequestParam("code") String code,
                                      HttpServletResponse response) {

        WxOAuth2AccessToken accessToken;
        WxOAuth2UserInfo wxUserInfo;
        try {
            //通过获取到的code换取token
            accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
            //通过token获取用户信息
            wxUserInfo = wxMpService.getOAuth2Service().getUserInfo(accessToken, null);
        } catch (WxErrorException e) {
            throw new SellException(ResultEnum.WECHAT_AUTH_FAIL);
        }

        //根据openId查找用户是否存在
        User user = userService.findByOpenId(wxUserInfo.getOpenid());

        if (null == user) {
            //如果用户不存在 则保存用户至数据库
            user = new User();
            user.setHeadPortrait(wxUserInfo.getHeadImgUrl());
            user.setOpenId(wxUserInfo.getOpenid());
            user.setNickname(wxUserInfo.getNickname());
            user = userService.save(user);
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return ResultUtil.success(userVO);
    }

    //更新用户信息
    @PostMapping("updateUserInfo")
    public Result<UserVO> updateUserInfo(@Valid UserForm userForm,
                                         BindingResult bindingResult,
                                         HttpServletRequest request) {

        //校验提交信息
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.USER_PARAMS_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        //获取请求中前置切面保存的用户信息
        UserVO userVO = (UserVO) request.getAttribute("userVO");
        //校验当前操作的用户和要更改的目标用户是否是同一人 防止越权
        if (!userVO.getId().equals(userForm.getId())) {
            throw new SellException(ResultEnum.ILLEGAL_OPERATION);
        }

        //执行更新操作
        User result = userService.updateUserInfo(userForm);

        if (result == null) {
            throw new SellException(ResultEnum.USER_UPDATE_FAIL);
        }

        //返回UserVO对象 去掉敏感信息
        BeanUtils.copyProperties(result, userVO);

        return ResultUtil.success(userVO);
    }


    //更新用户密码
    @PostMapping("updatePassword")
    public Result<UserVO> updatePassword(@Valid PasswordForm passwordForm,
                                         BindingResult bindingResult,
                                         HttpServletRequest request) {

        //校验提交信息
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.USER_PARAMS_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        //获取请求中前置切面保存的用户信息
        UserVO userVO = (UserVO) request.getAttribute("userVO");
        //校验当前操作的用户和要更改的目标用户是否是同一人 防止越权
        if (!userVO.getId().equals(passwordForm.getId())) {
            throw new SellException(ResultEnum.ILLEGAL_OPERATION);
        }

        //执行更新操作
        User result = userService.updatePassword(passwordForm);

        if (result == null) {
            throw new SellException(ResultEnum.USER_UPDATE_FAIL);
        }

        //返回UserVO对象 去掉敏感信息
        BeanUtils.copyProperties(result, userVO);

        return ResultUtil.success(userVO);
    }

    //前端用户微信授权首次登录 需先设置用户名
    @PostMapping("setUsername")
    public Result<UserVO> updateUsername(@RequestParam("id") Integer id,
                                         @RequestParam("username") String username) {

        User user = userService.setUsername(id, username);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return ResultUtil.success(userVO);
    }


    //用户绑定微信账号
    @PostMapping("bindWechat")
    public Result<UserVO> bindWechatAccount(@RequestParam("id") Integer id,
                                            @RequestParam("code") String code) {

        WxOAuth2AccessToken accessToken;
        WxOAuth2UserInfo wxUserInfo;
        try {
            //通过获取到的code换取token
            accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
            //通过token获取用户信息
            wxUserInfo = wxMpService.getOAuth2Service().getUserInfo(accessToken, null);
        } catch (WxErrorException e) {
            throw new SellException(ResultEnum.WECHAT_AUTH_FAIL);
        }

        User user = userService.bindWechatAccount(id, wxUserInfo.getOpenid(), wxUserInfo.getHeadImgUrl());

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return ResultUtil.success(userVO);
    }
}
