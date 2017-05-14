package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.EndCaseDto;
import com.erp.response.BaseMessage;
import com.erp.service.impl.EndCaseService;

@Controller
@Scope("prototype")
@RequestMapping("endCase")
public class EndCaseController {
    
    @Autowired
    private EndCaseService endCaservice;
    
    @RequestMapping(value = "uploadEndCase", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage uploadEndCase(@RequestBody final EndCaseDto endCaseDto) {
        return endCaservice.uploadEndCase(endCaseDto);
    }
    

}
