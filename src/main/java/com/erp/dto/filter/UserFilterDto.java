package com.erp.dto.filter;

import java.io.Serializable;

/**
 * 
 *
 * @author 王建观
 * @since 2015年12月11日 下午2:52:32
 */
public class UserFilterDto implements Serializable {

    private static final long serialVersionUID = -1508514225698812067L;
    
    private String loginName;

    private String username;
    
    private String mobile;
    
    private String roleId;
    

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
