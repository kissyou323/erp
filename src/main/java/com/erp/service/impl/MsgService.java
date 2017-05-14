package com.erp.service.impl;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.dto.MsgDto;
import com.erp.orm.domain.Msg;
import com.erp.orm.domain.User;
import com.erp.orm.repository.MsgRepository;
import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;
import com.erp.service.specification.MsgSpecification;

@Service
public class MsgService {

	private Logger logger = LoggerFactory.getLogger(MsgService.class);

	@Autowired
	private MsgRepository msgRepository;
	@Autowired
	private HttpSession httpSession;

	/**
	 * 新增
	 * 
	 * @param msgDto
	 * @return
	 */
	@Transactional
	public BaseMessage save(MsgDto msgDto) {
		BaseMessage message = null;
		Msg msg = new Msg();
		try {
			message = new BaseMessage(MessageCode.SUCCESSED);
			BeanUtils.copyProperties(msgDto, msg);
			msgRepository.save(msg);
			logger.info("添加消息成功");
		} catch (Exception e) {
			message = new BaseMessage(MessageCode.FAILED);
		}
		return message;
	}

	/**
	 * 列表
	 * 
	 * @param userLogin
	 * @param type
	 * @return
	 */
	public BaseMessage findMsg(MsgDto msgDto,Pageable pageable) {
		logger.info("service ==" + msgDto.toString());
		BaseMessage message = null;
		String username = "";
		User user = null;
		Page<Msg> page = null;
		long count = 0;
		try {
			message = new BaseMessage(MessageCode.SUCCESSED);
			try {
			    user = ((User) httpSession.getAttribute("login_user"));
				if (null == user) {
					message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
					logger.info("登录超时了");
					return message;
				} else {
				    username = user.getLoginName();
				    msgDto.setUserLogin(username);
				}
			} catch (Exception e) {
				message = new BaseMessage(MessageCode.USER_NOT_LOGIN);
				logger.info("登录异常，或者超时了");
				return message;
			}
			logger.info("---service + {}",msgDto.toString());
			MsgSpecification msgSpecification = new MsgSpecification(msgDto);
			page = msgRepository.findAll(msgSpecification, pageable);
            count = msgRepository.count(msgSpecification);
		} catch (Exception e) {
			e.printStackTrace();
			message = new BaseMessage(MessageCode.FAILED);
		}
		message.setData(page.getContent());
		message.setMsg(String.valueOf(count));
		return message;
	}

	/**
	 * 查询单条
	 * 
	 * @param id
	 * @return
	 */
	public Msg findById(Integer id) {
		return msgRepository.findOne(id);
	}
}
