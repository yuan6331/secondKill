package com.gdut.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="goods")
public class Goods implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="goods_id")
	private String goodsId;
	
	@Column(name="goods_name")
	private String goodsName;
	
	@Column(name="goods_price")
	private double goodsPrice;
	
	@Column(name="goods_img")
	private String goodsImg;
	
	@Column(name="goods_info")
	private String goodsInfo;
	
	@Column(name="goods_num")
	private int goodsNum;
	
	@Column(name="goods_create_date")
	private Date goodsCreateDate;
	
	@Column(name="goods_sell_time")
	private Date goodsSellTime;
	
}
