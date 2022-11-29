package com.example.jingdong.service;

import com.example.jingdong.form.PasswordForm;
import com.example.jingdong.pojo.Seller;
import com.example.jingdong.vo.SellerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellerService {


    Seller save(Seller seller);

    Seller login(String username, String password);

    /**
     * 根据openId查找卖家信息 用于扫码登录
     * @param openId  卖家openId
     * @return        Seller对象
     */
    Seller findByOpenId(String openId);


    /**
     * 根据用户名查找卖家信息
     * @param username      卖家用户名
     * @return              Seller对象
     */
    Seller findByUsername(String username);

    /**
     * 根据id查找卖家用户信息
     * @param sellerId  卖家用户信息
     * @return          Seller
     */
    Seller findBySellerId(Integer sellerId);


    /**
     * 店铺后台管理 查询店铺管理员
     * 返回SellerVO对象 去掉不宜展示的openId 密码等信息
     * @param realName  有值时根据真实姓名进行模糊查找
     * @param pageable  分页对象
     * @return          Page<SellerVO>
     */
    Page<SellerVO> findShopAdmin(String realName,Integer state, Pageable pageable);

    /**
     * 删除卖家用户
     * @param sellerId  卖家用户id
     */
    void delete(Integer sellerId);

    /**
     * 更新密码
     * @param passwordForm  密码更新表单对象
     * @return Seller
     */
    Seller updatePassword(PasswordForm passwordForm);
}
