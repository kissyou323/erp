package com.erp.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 5623380575087386621L;

 // id
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    // 登录名
    @Column(name = "login_name")
    private String loginName;

    // 用户名
    @Column(name = "user_name")
    private String userName;

    // 用户密码
    @Column(name = "password")
    private String password;

    // 手机
    @Column(name = "mobile")
    private String mobile;

    // 登录次数
    @Column(name = "count")
    private Integer count;

    // 创建时间
    @Column(name = "createAt")
    private Date createAt;
    
    //是否锁定
    @Column(name = "islock")
    private String isLock;
    
    //角色id，多个用逗号隔开
    @Column(name = "role_id")
    private String roleid;  //1民警 2.管理员 3.领导
    
    //权限ID，多个用逗号隔开
    @Column(name = "permission_id")
    private String permissionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		return com.alibaba.fastjson.JSON.toJSONString(this);
	} 

}
