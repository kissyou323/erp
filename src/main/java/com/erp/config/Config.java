package com.erp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

	@Value("${qrcode}")
	private String qrcode;

	@Value("${defaulPassword}")
	private String pwd;

	@Value("${upload_url}")
	private String upload_url;

	@Value("${upload_urlTmp}")
	private String upload_urlTmp;

	public String getPwd() {
		return pwd;
	}

	public String getQrcode() {
		return qrcode;
	}

	public String getUpload_url() {
		return upload_url;
	}

	public String getUpload_urlTmp() {
		return upload_urlTmp;
	}

}
