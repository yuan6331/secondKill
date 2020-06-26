package com.gdut.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdut.entity.Goods;

import tk.mybatis.mapper.common.IdsMapper;

@Mapper
public interface GoodsMapper extends tk.mybatis.mapper.common.Mapper<Goods> ,IdsMapper<Goods>{

}
