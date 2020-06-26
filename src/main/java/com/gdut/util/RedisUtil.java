package com.gdut.util;

import java.util.ArrayList;
import java.util.List;

import org.mockito.internal.stubbing.answers.ThrowsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.gdut.entity.Goods;

/**
 * @author gaoyuan
 *2020年6月26日
 *上午4:54:15
 */
@Component
public class RedisUtil {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@SuppressWarnings("unchecked")
	public void setList(String key, List<?> list) {
		if (key != null && list != null) {
			redisTemplate.opsForList().leftPushAll(key, list);
		}

	}

	@SuppressWarnings("unchecked")
	public List<?> getList(String key) {
		if (key != null && this.hasKey(key)) {
			return (List<?>) redisTemplate.opsForList().range(key, 0, -1);
		}
		return new ArrayList<Object>();
	}

	@SuppressWarnings("unchecked")
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public Object get(String key) {
		if (key != null) {
			return redisTemplate.opsForValue().get(key);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@SuppressWarnings("unchecked")
	public void delete(String key) {
		redisTemplate.delete(key);
	}

}
