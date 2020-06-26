package com.gdut.service;

import com.gdut.entity.Custom;

public interface CustomService {

	
	String checkUser(Custom c);
	
	Custom selectCustom(Custom c);
	
	boolean isExistUser(Custom c);
	
	 boolean isTruePwd(Custom c);
	 
	 void keepCustomId(String sessionId, Custom c);
	 
	 boolean isLogin();
	 
	 String getCustomId();
	 
	 void loginOff();
}
