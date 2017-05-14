package com.erp.response;


/** 
* @ClassName: MessageCode 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lihx 
* @date 2015年11月24日 下午2:29:31 
*/
public enum MessageCode {
    SUCCESSED(200, "提交成功"),
    NO_RESPONSE(100, "没有查询到结果"),
    FAILED(201, "提交失败"),

    PARAMS_ERROR(300, "参数不能为空或格式错误"),

    USER_NOT_LOGIN(302, "用户未登录或超时"),

    INTERFACE_NO_ACCESS(400, "没有此接口访问权限"),
    NO_RESOURCE(404, "请求的资源不存在"),
    USERNAME_PASSWORD_ERROR(440, "用户名密码错误"),
    USER_DISABLE(441, "当前用户不可用"),

    USER_NAME_NOT_UNIQUE(301, "用户名重复"),
    USER_ACCOUNT_NOT_UNIQUE(302, "用户接口帐号重复"),
    USER_NOT_AVAILABLE(303, "帐号当前不可用"),
    CATEGORY_NAME_NOT_NUIQUE(401,"接口分类名称重复"),
    ASSETSNO_NOT_NULL(501,"编号名称重复"),
    QRCODE_ERROR(5001,"二维码生成错误"),
    ISLOCK_OK(304,"已锁定"),
    SYSTEM_ERROR(9999, "系统错误"),
    NOT_CASENO(101, "合并主案件编号必须是子案件编号中的一个"),
    NOT_CASECASESTATUS(102, "合并案件中不允许存在两笔已合并案件(主案件)"),
    NOT_CASENOISNOT(103, "串并的案件中存在不合法的案件"),
    CASELISTNOREAPERT(104,"结案督案单已存在");
    
    

    private int code;
    private String msg;

    private MessageCode(int code, String msg) {
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
