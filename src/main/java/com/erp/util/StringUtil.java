package com.erp.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.erp.dto.CaseDto;

/**
 * 
 * @author liuyang
 *
 * @since 2015年12月11日下午3:06:44
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * 
     * @param str
     *            原始字符串
     * @return 结果
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHave(String[] strs, String s) {
        /*
         * 此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
         */
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].indexOf(s) != -1) {// 循环查找字符串数组中的每个字符串中是否包含所有查找的内容
                return true;// 查找到了就返回真，不在继续查询
            }
        }
        return false;// 没找到返回false
    }
    
    /** 
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty 
     *  
     * @param obj 
     * @return 
     */  
    public static boolean isNullOrEmpty(Object obj) {  
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullOrEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    }  
    
    /*** 
     * Determine whether the object's fields are empty 
     *  
     * @param obj 
     * @param isExcludeZero  :true:数值类型的值为0,则当做为空;----false:数值类型的值为0,则不为空 
     *  
     * @return 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     */ 
    public static boolean isNullObject(Object obj, boolean isExcludeZero)  
            throws SecurityException, IllegalArgumentException,  
            NoSuchFieldException, IllegalAccessException {  
        if(isNullOrEmpty(obj)){//对象本身就为null  
            return true;  
        }  
        List<Field> fieldList = getAllFieldList(obj.getClass());  
        boolean isNull = true;  
        for (int i = 0; i < fieldList.size(); i++) {  
            Field f = fieldList.get(i);  
            Object propertyValue = null;  
            try {  
                propertyValue = getObjectValue(obj, f);  
            } catch (NoSuchFieldException e) {  
                e.printStackTrace();  
            }  
  
            if (!isNullOrEmpty(propertyValue) && propertyValue != "") {// 字段不为空  
                if (propertyValue instanceof Integer) {// 是数字  
                    if (!((Integer) propertyValue == 0 && isExcludeZero)) {  
                        isNull = false;  
                        break;  
                    }  
                } else if (propertyValue instanceof Double) {// 是数字  
                    if (!((Double) propertyValue == 0 && isExcludeZero)) {  
                        isNull = false;  
                        break;  
                    }  
                }else if (propertyValue instanceof Float) {// 是数字  
                    if (!((Float) propertyValue == 0 && isExcludeZero)) {  
                        isNull = false;  
                        break;  
                    }  
                }else if (propertyValue instanceof Short) {// 是数字  
                    if (!((Short) propertyValue == 0 && isExcludeZero)) {  
                        isNull = false;  
                        break;  
                    }  
                }else {  
                    isNull = false;  
                    break;  
                }  
            }  
        }  
        return isNull;  
    }  
    
    public static List<Field> getAllFieldList(Class<?> clazz) {
        List<Field> fieldsList = new ArrayList<Field>();// return object
        if (clazz == null) {
            return null;
        }

        Class<?> superClass = clazz.getSuperclass();// father class
        if (!superClass.getName().equals(Object.class.getName()))/*
                                                                 * java.lang.Object
                                                                 */{

            // System.out.println("has father");
            fieldsList.addAll(getAllFieldList(superClass));// Recursive
        }
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            // 排除因实现Serializable 接口而产生的属性serialVersionUID
            if (!field.getName().equals("serialVersionUID")) {
                fieldsList.add(field);
            }
        }
        return fieldsList;
    }
    
    /***
     * 获取指定对象的属性值
     * 
     * @param obj
     * @param name
     *            :Field
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getObjectValue(Object obj, Field name)
            throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {

        // Field f = getSpecifiedField(obj.getClass(), name.getName());
        if (name == null) {
            System.out.println("[ReflectHWUtils.getObjectValue]"
                    + obj.getClass().getName() + " does not has field " + name);
            return null;
        }
        name.setAccessible(true);
        return name.get(obj);
    }
    
    public static void main(String[] args) {
//        isHave
        CaseDto caseDto =  new CaseDto();
        String[] caseNos = new String[]{"A2018111111","A2018111112"};
        caseDto.setCaseNo("A2018111111");
        caseDto.setCaseNos(caseNos);
        if (!StringUtil.isHave(caseDto.getCaseNos(), caseDto.getCaseNo())){
            System.out.println("1");
        } else {
            System.out.println("2");
        }
        System.out.println("3");
        
        
//        SuspectDto s = new SuspectDto();
//        s.setCaseNo("");
//        s.setId(1);
//        s.setIdentity("1");
//        try {
//            System.out.println(isNullObject(s,true));
//        } catch (SecurityException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

}
