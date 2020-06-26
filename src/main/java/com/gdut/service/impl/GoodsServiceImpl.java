package com.gdut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdut.entity.Goods;
import com.gdut.mapper.GoodsMapper;
import com.gdut.service.GoodsService;
import com.gdut.util.RedisUtil;

import net.bytebuddy.asm.Advice.This;
import tk.mybatis.mapper.entity.Example;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	RedisUtil redisUtil;
	
	
	public Example getExample(String goodsId){
		Example e = new Example(Goods.class);
		Example.Criteria criteria = e.createCriteria();
		criteria.andEqualTo("goodsId",goodsId);
		return e;
	}
	
	// 首次访问商品列表，将所有商品数据存入Redis
	@Override
	public void saveGoodsInRedis(String key) {
		List<Goods> goodsList = goodsMapper.selectAll();
		redisUtil.setList(key, goodsList);
	}

	@Override
	public Goods getGoods(String goodsId) {
		Goods g = goodsMapper.selectByPrimaryKey(goodsId);
		return g;
	}

	public Goods selectById(String id) {
		return goodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Goods> selectAll() {
		return null;
	}

	@Override
	public int updateGoodsNum(String goodsId, int num) {
		Goods goods = selectById(goodsId);
		goods.setGoodsNum(num);
		int updateResult = goodsMapper.updateByExample(goods,getExample(goodsId));
		return updateResult;
	}

	@Override
	public Goods selectOne(String goodsId) {
		return goodsMapper.selectByPrimaryKey(goodsId);
	}

}
