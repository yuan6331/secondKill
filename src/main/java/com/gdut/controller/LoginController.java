package com.gdut.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gdut.entity.Custom;
import com.gdut.service.TurnTableService;
import com.gdut.service.impl.CustomServiceImpl;
import com.gdut.util.RedisUtil;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	CustomServiceImpl CustomService;

	@Autowired
	TurnTableService turnTableService;

	@Autowired
	private RedisUtil redisUtil;

	/*
	 * 登录验证
	 * 
	 */
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST) // ,produces="application/json;charset=utf8"
	public String login(@Param(value = "customId") String customId, @Param(value = "customPwd") String customPwd,
			Model model) {
		Custom custom = new Custom(null, customId, customPwd, null);
		// 登录信息验证
		return CustomService.checkUser(custom);
	}

	Map<String, Object> cusotmMap = new HashMap<String, Object>();

	@RequestMapping(value = "/customPage")
	public ModelAndView loginSuccess(ModelAndView model) {
		if (CustomService.isLogin()) {
			model.addObject("custom",redisUtil.get("custom"));
			model.setViewName("/customPage");
		} else {
			model.setViewName("/index");
		}
		return model;
	}

	/*
	 * 手动登出
	 * 
	 */
	@RequestMapping(value = "/offLine")
	public ModelAndView loginOff(ModelAndView mv) {
		if (CustomService.isLogin()) {
			CustomService.loginOff();
		}
		cusotmMap.remove("customId");
		mv.setViewName("index");
		return mv;
	}

	/*
	 * @RequestMapping(value="/createCustom") public String create() {
	 * loginService.createCustom(); return "success";
	 * 
	 * }
	 */

}
