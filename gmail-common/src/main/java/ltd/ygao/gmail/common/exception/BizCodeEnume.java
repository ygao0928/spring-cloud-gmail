package ltd.ygao.gmail.common.exception;

/**
 * 系统错误状态码
 * 错误列表：
 * 10：通用
 * 11：商品
 * 12：订单
 * 13：购物车
 * 14：物流
 *
 * @author Kevin
 * @version 1.0
 * @date 2020/5/26 11:44
 */
public enum BizCodeEnume {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式校验失败"),
    PRODUCT_UP_EXCEPTION(11000,"商品上架异常");
    private int code;
    private String msg;

    BizCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
