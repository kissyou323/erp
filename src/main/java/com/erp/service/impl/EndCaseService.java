package com.erp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.EndCaseDto;
import com.erp.orm.domain.EndCase;
import com.erp.orm.repository.EndCaseRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;

@Service
public class EndCaseService {

    private Logger logger = LoggerFactory.getLogger(EndCaseService.class);
    
    @Autowired
    private EndCaseRepository endCaseRepository;

    public BaseMessage uploadEndCase(EndCaseDto endCaseDto) {

        BaseMessage message = null;
        String caseAddUrl = endCaseDto.getCaseAddUrl();
        if (null == caseAddUrl) {
            message = new BaseMessage(MessageCode.FAILED);
            message.setDesc("请上传结案文件内容");
            logger.error("请上上传 结案文件内容");
            return message;
        }
        
        EndCase endCase = new EndCase();
        endCase.setCaseAddUrl(endCaseDto.getCaseAddUrl());
        endCase.setCaseNo(endCaseDto.getCaseNo());
        endCase.setEndcaseExplain(endCaseDto.getEndcaseExplain());
        endCase = endCaseRepository.save(endCase);
        message = new BaseMessage(MessageCode.SUCCESSED);
        message.setData(new EndCaseDto(endCase));
        message.setDesc("上传结案文件成功");
        logger.info("新增结案文件 id:{} 成功", endCase.getCaseId());
        return message;

    }

}
