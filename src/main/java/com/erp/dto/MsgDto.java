package com.erp.dto;

import java.util.Date;

import com.alibaba.fastjson.JSON;

public class MsgDto {

	private int id;

	private String content;

	private Date startTime;
	
	private Date endTime;

	private String userLogin;

	private Integer type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public static void main(String[] args) {
		MsgDto m = new MsgDto();
		m.setUserLogin("admin");
		m.setType(1);
		System.out.println(m.toString());
	}
}
