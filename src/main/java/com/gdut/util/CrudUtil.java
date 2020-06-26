package com.gdut.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gdut.entity.Custom;
import com.gdut.mapper.CustomMapper;
import com.gdut.mapper.GoodsMapper;

import tk.mybatis.mapper.entity.Example;

@Component
public class CrudUtil {
	
	@Autowired
	CustomMapper customMapper;
	
	@Autowired
	static GoodsMapper goodsMapper;
	
	private static Example e = new Example(Custom.class);
	
	public static <T>T selectById(T t,String id){
		System.out.println(t.getClass().getSimpleName());
		Example.Criteria criteria = e.createCriteria();
		criteria.andEqualTo(t.getClass().getSimpleName()+"Id",id);
		
		
		return null;
	}
	
	//获取方法调用者
	 public String getCaller(){
		 return new Exception().getStackTrace()[1].getClassName();
       }  

}
