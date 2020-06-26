package com.gdut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.gdut.entity.Custom;
import com.gdut.mapper.CustomMapper;
import com.gdut.service.CustomService;
import com.gdut.util.RedisUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class CustomServiceImpl implements CustomService{

	@Autowired
	HttpServletResponse response;

	@Autowired
	HttpServletRequest request;

	@Autowired
	CustomMapper customMapper;

	@Autowired
	private RedisUtil redisUtil;

	public String checkUser(Custom c) {
		if (isExistUser(c)) {
			if (isTruePwd(c)) {
				redisUtil.set("custom", c);
				return "success";
			} else {
				return "密码错误！";
			}
		} else {
			return "此用户不存在！";
		}

	}

	public Custom selectCustom(Custom c) {
		Example e = new Example(Custom.class);
		Example.Criteria criteria = e.createCriteria();
		criteria.andEqualTo("customId", c.getCustomId() + "");
		List<Custom> cList = customMapper.selectByExample(e);
		if (cList.size() > 0) {
			return cList.get(0);
		} else {
			return null;
		}
	}

	// customId是否存在
	public boolean isExistUser(Custom c) {
		if (selectCustom(c) == null) {
			return false;
		} else {
			return selectCustom(c) != null;
		}

	}

	// 验证密码
	public boolean isTruePwd(Custom c) {
		return c.getCustomPwd().equals(selectCustom(c).getCustomPwd());
	}

	/*
	 * public void setCookie(Custom c) { String uuid =
	 * UUID.randomUUID().toString(); Cookie cookie = new Cookie("sessionId",
	 * uuid); cookie.setPath("/"); cookie.setMaxAge(20);
	 * response.addCookie(cookie); this.keepCustomId(uuid, c); }
	 */

	Map<String, String> sessionMap = new HashMap<String, String>();

	public void keepCustomId(String sessionId, Custom c) {
		sessionMap.put(sessionId, c.getCustomId() + "");
	}

/*	public String getUuid(HttpServletRequest request) {
		for (Cookie cookie : request.getCookies()) {
			if ("sessionId".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}*/

	public boolean isLogin() {
		boolean flag=this.getCustomId() != null;
		System.out.print("是否已登录： "+flag);
		return flag;
	}

	public String getCustomId() {
		if(redisUtil.hasKey("custom")){
			return ((Custom)redisUtil.get("custom")).getCustomId();
		}else{
			return null;
		}
		
	}

	public void loginOff() {
		redisUtil.delete("custom");
	}

	/*
	 * public void createCustom() { Custom custom = new Custom(); for (int i =
	 * 6331; i < 7331; i++) { System.out.println(i);
	 * custom.setId(UUID.randomUUID() + ""); custom.setCustomId("1562614" + i);
	 * custom.setCustomPwd("123456"); custom.setCustomPhone("15626146331");
	 * customMapper.insert(custom); } }
	 */

}
