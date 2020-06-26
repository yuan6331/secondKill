package com.gdut.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdut.entity.Custom;

import tk.mybatis.mapper.common.IdsMapper;

@Mapper
public interface CustomMapper extends tk.mybatis.mapper.common.Mapper<Custom> ,IdsMapper<Custom>{

}
