package com.gdut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gdut.entity.Goods;
import com.gdut.mapper.GoodsMapper;
import com.gdut.service.impl.GoodsServiceImpl;
import com.gdut.util.RedisUtil;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

	@Autowired
	GoodsServiceImpl GoodsService;

	@Autowired
	GoodsMapper goodsMapper;

	@Autowired
	RedisUtil redisUtil;

	@RequestMapping(value = "/googsList")
	public ModelAndView goodsList(ModelAndView mv) {

		if (!redisUtil.hasKey("goodsList") || redisUtil.getList("goodsList").size() < 1) {
			// 将所有商品存入 redis
			redisUtil.setList("goodsList", (List<Goods>) goodsMapper.selectAll());
		}
		mv.addObject("goodsList", redisUtil.getList("goodsList"));
		mv.setViewName("/goods/goodsList");
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView goodsInfo(ModelAndView mv, @PathVariable("id") String id)
			throws NoSuchMethodException, SecurityException {
		@SuppressWarnings("unchecked")
		List<Goods> goodsList = (List<Goods>) redisUtil.getList("goodsList");
		for (Goods g : goodsList) {
			System.out.println(id + "------" + g.getGoodsId());
			if (id.equals(g.getGoodsId())) {
				mv.addObject("goods", g);
				break;
			}
			mv.addObject("goods", g);
		}
		mv.setViewName("/goods/goodsDetail");
		return mv;
	}

	@RequestMapping(value = "/secondKill")
	public ModelAndView buyGoods(ModelAndView mv, String goodsId) {
		Goods goods = GoodsService.selectById(goodsId);
		if (goods.getGoodsNum() > 1) {

		}
		return mv;
	}

}
