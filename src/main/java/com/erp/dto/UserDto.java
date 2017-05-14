package com.erp.dto;

import java.util.Date;

import com.alibaba.fastjson.JSON;


public class UserDto {
        private Integer id;

        // 登录名
        private String loginName;

        // 用户名
        private String userName;

        // 用户密码
        private String password;

        // 手机
        private String mobile;

        // 登录次数
        private Integer count;

        // 创建时间
        private Date createAt;
        
        //是否锁定
        private String isLock;
        
        //角色id角色id 1民警 2管理员 3所内领导
        private String roleid;
        
        //权限ID
        private String permissionId;
        
        private String newPwd;
        
        
        
        public String getNewPwd() {
            return newPwd;
        }

        public void setNewPwd(String newPwd) {
            this.newPwd = newPwd;
        }

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
            return JSON.toJSONString(this);
        }
        
        public static void main(String[] args) {
            UserDto s = new UserDto();
            s.setLoginName("admin");
            s.setPassword("123456");
            System.out.println(s);
        }

}
