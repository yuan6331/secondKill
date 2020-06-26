package com.gdut.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdut.entity.Prize;

@Service
public class TurnTableService {
	
	@Autowired
	HttpServletResponse response;
	
	private static List<Object> prizeList = new ArrayList<Object>();
	
	public void mockPrize() {
		Prize p1 = new Prize("1","保温杯",1,"/images/3.png","fff");
		Prize p2 = new Prize("2","雨伞",1,"/images/4.png","eee");
		Prize p3 = new Prize("3","茅台",1,"/images/5.png","ddd");
		Prize p4 = new Prize("4","神秘物品B",1,"/images/6.png","ccc");
		Prize p5 = new Prize("5","橄榄油",1,"/images/7.png","bbb");
		Prize p6 = new Prize("6","20积分",1,"/images/8.png","aaa");
		prizeList.add(p1);
		prizeList.add(p2);
		prizeList.add(p3);
		prizeList.add(p4);
		prizeList.add(p5);
		prizeList.add(p6);
		
	}
	
	public List<Object> getPrize(){
		prizeList.clear();
		this.mockPrize();
		return prizeList;
	}
	
	public String prizeDraw() {
		List<Object> prizeList = new ArrayList<Object>();
		prizeList = getPrize();
		int size = prizeList.size();
		int prizeIndex = (int)(Math.random()*size);
		Prize prize = (Prize) prizeList.get(prizeIndex);
		return prize.getPrizeName();
		
	}
	
}
