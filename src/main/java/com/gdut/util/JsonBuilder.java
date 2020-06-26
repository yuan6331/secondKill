package com.gdut.util;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class JsonBuilder {

	public static JsonBuilder builder(){
		return new JsonBuilder();
	}
	
	public JSONObject string2json(String text) {
		try {
			return JSONObject.parseObject(text);
		} catch (Exception e) {
			new Exception("text不是JSON格式");
		}
		return null;
	}
	
	
}
