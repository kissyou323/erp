package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用页面跳转控制器
 * @author wang-jg
 *
 */
@RequestMapping("/page")
@Controller
public class PageController {
    
    /**
     * 客户端传递pageName参数就返回pageName页面
     * @param pageName
     * @return
     */
    @RequestMapping("/{pageName}")
    public String topage(@PathVariable("pageName") String pageName){
        return pageName;
    }
}
