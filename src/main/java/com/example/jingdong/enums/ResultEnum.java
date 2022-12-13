package com.example.jingdong.enums;

public enum ResultEnum implements BaseEnum<Object>{

    //系统
    UNKNOWN_ERROR(-1,"系统繁忙，请稍后再试"),
    PARAM_ERROR(10000,"参数错误"),
    UPLOAD_IMG_FAIL(10001,"文件上传失败"),
    NOT_LOGGED_IN(10002,"用户未登录"),
    KAPTCHA_CODE_ERROR(10003,"验证码错误"),
    ILLEGAL_OPERATION(10004,"非法操作"),
    REPEAT_PASSWORD_ERROR(10005,"重复密码不一致"),
    OLD_PASSWORD_ERROR(10006,"旧密码错误"),
    UPDATE_PASSWORD_FAIL(10007,"更新密码失败"),

    //店铺
    SHOP_CATEGORY_NOT_EXIST(20000,"店铺分类不存在"),
    SHOP_NOT_EXIST(20001,"店铺不存在"),
    CATEGORY_UPDATE_FAIL(20002,"分类信息更新失败"),

    //商品
    PRODUCT_CATEGORY_NOT_EXIST(30000,"商品分类不存在"),
    PRODUCT_NOT_EXIST(30001,"商品不存在" ),
    PRODUCT_STOCK_NOT_ENOUGH(30002,"库存不足"),
    PRODUCT_ADD_FAIL(30003,"商品添加失败"),
    PRODUCT_EDIT_FAIL(30004,"商品信息更新失败"),

    //用户
    USER_PARAMS_ERROR(40000,"用户参数错误"),
    USER_REP_PASSWORD_NOT_MATCH(40001,"重复密码不一致"),
    USER_ALREADY_EXIST(40002,"用户已存在"),
    ACCOUNT_HAS_A_USERNAME(40015,"该账号已设置用户名，请直接登录"),
    USER_REGISTER_ERROR(40003,"用户注册失败"),
    USER_REQUEST_ERROR(40004,"用户请求不合法"),
    USER_PASSWORD_ERROR(40005,"用户密码不正确"),
    USER_NOT_EXIST(40006,"该用户不存在"),
    USER_UPDATE_FAIL(40007,"更新用户信息失败"),
    USER_NOT_LOGGED_IN(40008,"用户未登录"),
    USER_HAS_BEEN_LOCKED(40009,"用户已被禁用"),
    USER_HAS_BEEN_BOUND(40011,"该账号已绑定微信"),
    WECHAT_HAS_BEEN_BOUND(123,"该微信已绑定账号，请先解绑"),
    USER_BOUNDING_FAIL(40012,"账号绑定失败"),
    USER_WECHAT_NOT_AUTH(40013,"用户微信未授权，请先用微信登录再操作"),
    USERNAME_LENGTH_ERROR(40014,"用户名长度不合法，应在6-18之间"),
    PASSWORD_NOT_EXIST(40015,"用户未设置密码，请先使用微信登录修改密码"),



    //微信授权
    WECHAT_MP_ERROR(50000,"微信公众号发生错误"),
    WECHAT_AUTH_FAIL(50001,"微信授权失败"),

    //收货地址
    ADDRESS_NOT_EXIST(60000,"地址不存在"),
    ADDRESS_PARAM_ERROR(60001,"地址参数错误"),
    ADDRESS_ADD_ERROR(60002,"地址添加失败"),
    ADDRESS_UPDATE_ERROR(60003,"地址更新失败"),
    ADDRESS_USER_ERROR(60004,"非法操作"),
    ADDRESS_DEFAULT_EXIST(60005,"默认地址已存在"),
    ADDRESS_DEFAULT_NOT_EXIST(60006,"未设置默认地址"),

    //订单
    ORDER_NOT_EXIST(70000,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(70001,"订单详情不存在,或商品已下架"),
    ORDER_STATE_ERROR(70002,"订单状态不正确"),
    ORDER_UPDATE_FAIL(70003,"订单更新失败"),
    ORDER_DETAIL_EMPTY(70004,"订单详情为空"),
    ORDER_PAY_STATE_ERROR(70005,"订单支付状态不正确,操作失败"),
    ORDER_PARAM_ERROR(70006,"订单参数错误"),
    ORDER_PRODUCT_EMPTY(70007,"订单商品为空"),
    ORDER_OWNER_ERROR(70008,"订单不属于当前用户"),
    ORDER_PAY_NOT(70009,"订单未支付"),
    ORDER_HAS_BEEN_PAID(70010,"订单已支付"),
    ORDER_TRANSACTION_NUMBER_ERROR(70011,"订单交易流水号不可为空"),

    //微信支付
    WXPAY_NOTIFY_MONEY_VARIFY_ERROR(80000,"微信支付异步通知金额校验不通过"),
    WXPAY_NOTIFY_OPENID_VARIFY_ERROR(80001,"微信支付异步通知openId校验不通过"),
    WXPAY_REFUND_ERROR(80002,"微信退款失败"),
    WXPAY_REFUND_ERROR_SHIPPED(80003,"已发货不可退款，请联系商家"),

    //卖家
    LOGIN_FAIL(90001,"登录失败，信息不正确"),
    LOGOUT_SUCCESS(90002,"登出成功"),
    USER_NOT_EXIST_OR_AUTH(90003,"用户信息不存在或未授权"),
    ADMIN_RIGHTS_NOT_ENOUGH(90004,"管理员权限不足"),
    ADMIN_UPDATE_FAIL(90005,"管理员信息更新失败"),
    ADMIN_STATE_ERROR(90006,"管理员账户已被停用"),
    UNABLE_DISABLE_YOURSELF(90007,"无法禁用自己"),
    UNABLE_DELETE_YOURSELF(90008,"无法删除自己"),
    ADMIN_DELETE_FAIL(90009,"管理员删除失败"),
    QRLOGIN_BIND_SUCCEED(90010,"微信扫码登录绑定成功"),
    PASSWORD_LENGTH_ERROR(900010,"密码长度应在6-18之间"),
    ;


    public final Integer code;
    public final String message;

    ResultEnum(Integer code , String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
