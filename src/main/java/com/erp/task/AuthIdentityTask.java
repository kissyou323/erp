package com.erp.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.erp.constant.Constant;
import com.erp.dto.MsgDto;
import com.erp.orm.domain.Record;
import com.erp.service.impl.MsgService;
import com.erp.service.impl.RecordService;

/**
 * 定时任务
 * @author webstorm
 *
 */
public class AuthIdentityTask {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private RecordService recordService;
    
    @Autowired
    private MsgService msgService;
    
    /**
     * 任务执行体，主要执行的业务逻辑
     */
    public void  execute(){
        
        logger.info("start task");
        
        //每天下午
        MsgDto msgDto = null;
        List<Record> list = recordService.findRecordList();
        if (null!=list) {
        	msgDto = new MsgDto();
        	for (Record record : list ) {
        	    String userName = "";
        	    String useDepartment = record.getUseDepartment();
        	    logger.info("useDepartment " + useDepartment);
        	    if (!StringUtils.isEmpty(useDepartment)) {
        	        //截取使用人
        	        String[] strs = useDepartment.split(";");
        	        logger.info("strs " + strs[0]);
        	        userName = strs[0];
        	        logger.info("userName " + userName);
        	    }
        		String str = "尊敬的【"+record.getUseDepartment()+"】同志，您好，您于【"+record.getRecordTime()+"】，在【"+record.getAgent()+"】处借了【"+record.getRecordName()+"】，尚未归还，请在下班前到管理员处归还。谢谢！";
        		logger.info(str);
        		msgDto.setContent(str);
        		msgDto.setStartTime(new Date());
        		msgDto.setUserLogin(userName);
        		msgDto.setType(Constant.MSG_TYPE_1);
        		msgService.save(msgDto);
        		
        		//通知Admin用户
        		String adminStr = "尊敬的管理员同志，【" + record.getUseDepartment() + "】同志于【"+record.getRecordTime() + "】在【" + record.getAgent()+"】处借了【"+record.getRecordName()+"】，尚未归还，请在督促其处归还。谢谢！";
        		logger.info(adminStr);
                msgDto.setContent(adminStr);
                msgDto.setStartTime(new Date());
                msgDto.setUserLogin(Constant.ADMIN);
                msgDto.setType(Constant.MSG_TYPE_1);
                msgService.save(msgDto);
            }
        }
    }
}
