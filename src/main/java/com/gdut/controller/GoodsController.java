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
	GoodsServiceImpl goodsService;

	@Autowired
	GoodsMapper goodsMapper;

	@Autowired
	RedisUtil redisUtil;

	@RequestMapping(value = "/googsList")
	public ModelAndView goodsList(int pageNum, ModelAndView mv) {

		
		mv.addObject("goodsList", goodsService.selectAll());
		mv.setViewName("/goods/goodsList");
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView goodsInfo(ModelAndView mv, @PathVariable("id") String id)	{
		mv.addObject("goods", goodsService.getGoods(id));
		mv.setViewName("/goods/goodsDetail");
		return mv;
	}

	@RequestMapping(value = "/secondKill")
	public ModelAndView buyGoods(ModelAndView mv, String goodsId) {
		//下单对应数据库
		Goods goods = goodsService.selectById(goodsId);
		if (goods.getGoodsNum() > 1) {

		}
		return mv;
	}

}
