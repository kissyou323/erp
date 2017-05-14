package com.erp.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.erp.constant.Constant;
import com.erp.dto.MsgDto;
import com.erp.orm.domain.Case;
import com.erp.orm.domain.CaseList;
import com.erp.orm.domain.Possessions;
import com.erp.service.impl.CaseListService;
import com.erp.service.impl.CaseService;
import com.erp.service.impl.MsgService;
import com.erp.service.impl.PossessionsService;

/**
 * 定时任务
 * @author webstorm
 *
 */
public class CaseListTask {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MsgService msgService;
    
    @Autowired
    private CaseListService caseListService;
    
    @Autowired
    private CaseService caseService;

    @Autowired
    private PossessionsService possessionsService;
    
    /**
     * 任务执行体，主要执行的业务逻辑
     */
    public void  execute(){
        
        logger.info("每天早上9点通知民警今天要处理完成的督案单");
        MsgDto msgDto = null;
        List<CaseList> list = caseListService.findCaseList();
        if (null!=list) {
        	msgDto = new MsgDto();
        	for (CaseList caseList : list ) {
        		String str = "尊敬的【"+caseList.getPoliceName()+"】同志，您好，您的督案单编号为【"+caseList.getCaselistNo()+"】的案件将于今天要求完成，请在下班前处理。谢谢！";
        		logger.info(str);
        		msgDto.setContent(str);
        		msgDto.setStartTime(new Date());
        		msgDto.setUserLogin(caseList.getPoliceName());
        		msgDto.setType(Constant.MSG_TYPE_3);
        		msgService.save(msgDto);
            }
        }
        logger.info("每天早上9点通知民警名下有哪些案件需要在30天内处理完的，已经第23天了");
        MsgDto message = null;
        List<Case> cases = caseService.getEndCase_23();
        if (null!=cases) {
            message = new MsgDto();
            for (Case c : cases ) {
                //通知民警
                String str = "尊敬的【"+c.getPoliceName()+"】同志，您好，您的案件编号为【"+c.getCaseNo()+"】的案件已经第23天了，请尽快处理。谢谢！";
                logger.info(str);
                message.setContent(str);
                message.setStartTime(new Date());
                message.setUserLogin(c.getPoliceLoginName());
                message.setType(Constant.MSG_TYPE_3);
                msgService.save(message);
                //通知管理员
                String strs = "尊敬的管理员同志，您好，由【"+c.getPoliceName()+"】同志处理的案件编号为【"+c.getCaseNo()+"】的案件已经超过23天了，尚未结案，请督促尽快处理。谢谢！";
                logger.info(strs);
                message.setContent(str);
                message.setStartTime(new Date());
                message.setUserLogin(Constant.ADMIN);
                message.setType(Constant.MSG_TYPE_3);
                msgService.save(message);
            }
        }
        logger.info("每天早上9点通知涉案财物保管员，有存在移交半年以上的收缴涉案财物需要销毁");
        MsgDto ms = null;
        List<Possessions> PossessionsList = possessionsService.getPossessionsStaut_7();
        if (null!=cases) {
            ms = new MsgDto();
            for (Possessions possessions : PossessionsList ) {
                //通知保管员
                String str = "尊敬的【"+possessions.getKeeper()+"】同志，您好，由您保管的编号为【"+possessions.getPossessionsNo()+"】的涉案财物已经移交半年以上，请及时销毁。谢谢！";
                logger.info(str);
                ms.setContent(str);
                ms.setStartTime(new Date());
                ms.setUserLogin(possessions.getKeeper());
                ms.setType(Constant.MSG_TYPE_1);
                msgService.save(ms);
                //通知admin
                String strs = "尊敬的管理员同志，您好，由您保管的编号为【"+possessions.getPossessionsNo()+"】的涉案财物已经移交半年以上，请及时销毁。谢谢！";
                logger.info(strs);
                ms.setContent(strs);
                ms.setStartTime(new Date());
                ms.setUserLogin(Constant.ADMIN);
                ms.setType(Constant.MSG_TYPE_1);
                msgService.save(ms);
            }
        }
        
    }
    
}
