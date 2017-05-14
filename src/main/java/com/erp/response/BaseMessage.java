package com.erp.response;


/** 
* @ClassName: BaseMessage 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lihx 
* @date 2015年11月24日 下午2:29:12 
*/
public class BaseMessage extends Message {
    private Object data;

    public BaseMessage() {
        super();
    }

    public BaseMessage(MessageCode messageCode) {
        this.code = messageCode.getCode();
        this.msg = messageCode.getMsg();
    }

    public BaseMessage(int code, String msg) {
        super(code, msg);
    }

    public BaseMessage(int code, String msg, Object data) {
        this(code, msg);
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseMessage [data=" + data + "]";
    }
    
}
