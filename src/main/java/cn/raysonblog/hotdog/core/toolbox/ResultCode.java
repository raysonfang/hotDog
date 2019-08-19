package cn.raysonblog.hotdog.core.toolbox;

/**
 * 
 * <p>Title: ResultCode</p>
 * <p>Description: 结果状态码枚举类</p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-02-28 10:39
 * @version 1.0
 */
public enum ResultCode {
	
	SUCCESS("10000", "操作成功"),
	FAILED("10001", "操作失败"),
	
	PARAMERROR("10002", "参数错误"),
	LoginNameOrPassWordError("10003", "用户名或密码错误"),
	WEAK_NET_WORK("10004", "网络异常, 请稍后重试"),
	LOGIN_TIMEOUT("10005", "登陆超时, 请重新登陆"),
	ERROR("10006", "操作异常");
	private String code;
    private String msg;
    
	private ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
