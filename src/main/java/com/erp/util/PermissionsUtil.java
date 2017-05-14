package com.erp.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionsUtil {


    //民警接口
    private static String[] policePer = { "user_list" };
   
    // 管理员接口权限
    private static String[] adminPer = { "case_list" };

     //领导接口
    private static String[] leaderPer = {"case_list"};



    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<String> getPermissions(String roleId) {
        List<String> list = null;
        if (roleId.equals(1)) {
            //民警
            list = new ArrayList(Arrays.asList(policePer));
        } else if (roleId.equals(2)) {
            //管理员
            list = new ArrayList(Arrays.asList(adminPer));
        } else if (roleId.equals(3)) {
            //领导
            list = new ArrayList(Arrays.asList(leaderPer));
        } 
        return list;
    }

}
