package com.gdut.entity;

import java.io.Serializable;

public class Prize implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prizeId;
	private String prizeName;
	private int prizeNum;
	private String prizeImg;
	private String prizeDetail;
	public String getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public int getPrizeNum() {
		return prizeNum;
	}
	public void setPrizeNum(int prizeNum) {
		this.prizeNum = prizeNum;
	}
	public String getPrizeImg() {
		return prizeImg;
	}
	public void setPrizeImg(String prizeImg) {
		this.prizeImg = prizeImg;
	}
	public String getPrizeDetail() {
		return prizeDetail;
	}
	public void setPrizeDetail(String prizeDetail) {
		this.prizeDetail = prizeDetail;
	}
	public Prize(String prizeId, String prizeName, int prizeNum, String prizeImg, String prizeDetail) {
		super();
		this.prizeId = prizeId;
		this.prizeName = prizeName;
		this.prizeNum = prizeNum;
		this.prizeImg = prizeImg;
		this.prizeDetail = prizeDetail;
	}
	public Prize() {
		super();
	}
	@Override
	public String toString() {
		return "Prize [prizeId=" + prizeId + ", prizeName=" + prizeName + ", prizeNum=" + prizeNum + ", prizeImg="
				+ prizeImg + ", prizeDetail=" + prizeDetail + "]";
	}
	
	
	
}
