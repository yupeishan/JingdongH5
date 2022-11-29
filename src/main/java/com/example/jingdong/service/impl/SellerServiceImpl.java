package com.example.jingdong.service.impl;

import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.exception.SellerAuthException;
import com.example.jingdong.form.PasswordForm;
import com.example.jingdong.pojo.Seller;
import com.example.jingdong.repository.SellerRepository;
import com.example.jingdong.service.SellerService;
import com.example.jingdong.utils.Md5Util;
import com.example.jingdong.vo.SellerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository repository;


    @Override
    public Seller save(Seller seller) {
        return repository.save(seller);
    }

    @Override
    public Seller login(String username, String password) {
        //查找卖家信息
        Seller seller = findByUsername(username);
        if (seller == null){
            throw new SellerAuthException(ResultEnum.USER_NOT_EXIST.getMessage());
        }

        //校验密码是否正确
        if( !seller.getPassword().equals(Md5Util.md5(password)) ){
            throw new SellerAuthException(ResultEnum.USER_PASSWORD_ERROR.getMessage());
        }

        //设置卖家用户最后一次登录时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        seller.setLastLogin(formatter.format(date));

        return save(seller);
    }

    @Override
    public Seller findByOpenId(String openId) {

        return repository.findByOpenId(openId);
    }


    @Override
    public Seller findByUsername(String username) {

        return repository.findByUsername(username);
    }


    @Override
    public Seller findBySellerId(Integer sellerId) {
        Optional<Seller> optional = repository.findById(sellerId);

        if (!optional.isPresent()){
            throw new SellException(ResultEnum.USER_NOT_EXIST_OR_AUTH);
        }

        return optional.get();
    }


    @Override
    public Page<SellerVO> findShopAdmin(String realName,Integer state, Pageable pageable) {

        Page<Seller> sellerPage = repository.findAllByRealNameLikeAndState("%"+realName+"%", state, pageable);

        //将seller转换为sellerVO
        List<SellerVO> sellerVOList = sellerPage.stream().map(this::sellerToSellerVO).collect(Collectors.toList());

        return new PageImpl<>(sellerVOList,pageable,sellerPage.getTotalElements());
    }

    @Override
    public void delete(Integer sellerId) {
        try {
            repository.deleteById(sellerId);
        } catch (Exception e) {
            throw new SellException(ResultEnum.ADMIN_DELETE_FAIL);
        }
    }


    @Override
    public Seller updatePassword(PasswordForm passwordForm) {
        //校验重复密码是否一致
        if (!passwordForm.getPassword().equals(passwordForm.getRePassword())){
            throw new SellException(ResultEnum.REPEAT_PASSWORD_ERROR);
        }

        //查找要修改的卖家用户信息
        Optional<Seller> optional = repository.findById(passwordForm.getId());

        //校验用户是否存在
        if (!optional.isPresent()){
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }

        Seller seller = optional.get();
        //校验旧密码是否正确
        if (!seller.getPassword().equals(Md5Util.md5(passwordForm.getOldPassword()))){
            throw new SellException(ResultEnum.OLD_PASSWORD_ERROR);
        }

        //信息正确 赋值信息 更新用户密码
        seller.setPassword(Md5Util.md5(passwordForm.getPassword()));

        return repository.save(seller);
    }

    //将seller转换为sellerVO
    private SellerVO sellerToSellerVO(Seller seller){
        SellerVO sellerVO = new SellerVO();
        BeanUtils.copyProperties(seller,sellerVO);

        return sellerVO;
    }

}
