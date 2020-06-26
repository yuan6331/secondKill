package com.gdut.aop;

import org.apache.ibatis.javassist.expr.NewArray;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.gdut.service.impl.CustomServiceImpl;

@Component
@Aspect
public class LoginAop {

	@Autowired
	CustomServiceImpl customService;

	/*
	 * 定义一个切入点
	 */
	@Pointcut("execution(public * com.gdut.controller.SecondKillController.*(..))")
	public void pointCut() {}

	/*
	 * 通过连接点切入
	 * method前判断是否已登录
	 */
	@Around("pointCut()")
	public Object loginValidate(ProceedingJoinPoint pjp) throws Throwable {
		System.err.println("执行了isLogin验证");
		if (customService.isLogin()) {
			return pjp.proceed();
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("notLogin","请先登录账号！");
			mv.setViewName("/index");
			return mv;
		}

	}
}
