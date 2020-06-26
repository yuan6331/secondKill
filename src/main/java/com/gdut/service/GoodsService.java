package com.gdut.service;

import java.util.List;

import com.gdut.entity.Goods;

public interface GoodsService {
	
	List<Goods> selectAll();
	
	int updateGoodsNum(String goodsId,int num);
	
	Goods selectOne(String goodsId);
	
	//查询所以可以秒杀的商品
	void saveGoodsInRedis(String key);
	
	//查询指定的秒杀商品
	Goods getGoods(String goodsId);
}
