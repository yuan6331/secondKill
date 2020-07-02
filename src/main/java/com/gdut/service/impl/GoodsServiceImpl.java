package com.gdut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdut.entity.Goods;
import com.gdut.mapper.GoodsMapper;
import com.gdut.service.GoodsService;
import com.gdut.util.RedisUtil;
import com.github.pagehelper.PageHelper;

import net.bytebuddy.asm.Advice.This;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Administrator 2020年7月2日 上午1:11:51
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	GoodsMapper goodsMapper;

	@Autowired
	RedisUtil redisUtil;

	public Example getExample(String goodsId) {
		Example e = new Example(Goods.class);
		Example.Criteria criteria = e.createCriteria();
		criteria.andEqualTo("goodsId", goodsId);
		return e;
	}

	// 首次访问商品列表，将所有商品数据存入Redis
	@Override
	public void saveGoodsInRedis(int start, int pageSize, String key) {
		if (!redisUtil.hasKey("goodsList") || redisUtil.getList("goodsList").size() < 1) {
			// 将所有商品存入 redis
			/* if(param1 != null){
			 PageHelper.startPage(1, 10);
			 list = userMapper.selectIf(param1);
			 } else {
			 list = new ArrayList<User>();
			 }*/
			PageHelper.startPage(1, 10);
			redisUtil.setList(key, goodsMapper.selectAll()); 
		}else{
			return;
		}
		
	}

	@Override
	public Goods getGoods(String goodsId) {
		List<Goods> goodsList = (List<Goods>) redisUtil.getList("goodsList");
		for (Goods g : goodsList) {
			if (goodsId != null && goodsId.equals(g.getGoodsId())) {
				return g;
			}
		}
		return null;
	}

	public Goods selectById(String id) {
		return goodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Goods> selectAll() {
		PageHelper.offsetPage(0, 5);
		List<Goods> list = goodsMapper.selectAll();
		System.out.print("------------------");
		System.out.println(list.size());
		return list;
	}

	@Override
	public int updateGoodsNum(String goodsId, int num) {
		Goods goods = selectById(goodsId);
		goods.setGoodsNum(num);
		int updateResult = goodsMapper.updateByExample(goods, getExample(goodsId));
		return updateResult;
	}

	@Override
	public Goods selectOne(String goodsId) {
		return goodsMapper.selectByPrimaryKey(goodsId);
	}

}
