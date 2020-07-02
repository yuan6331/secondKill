package com.gdut.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.github.pagehelper.Page;

	public class PageInfo<T> implements Serializable {
	    private static final long serialVersionUID = 1L;
	    //当前页
	    private int pageNum;
	    //每页的数量
	    private int pageSize;
	    //当前页的数量
	    private int size;
	 
	    //由于startRow和endRow不常用，这里说个具体的用法
	    //可以在页面中"显示startRow到endRow 共size条数据"
	 
	    //当前页面第一个元素在数据库中的行号
//	    private int startRow;
	    //当前页面最后一个元素在数据库中的行号
//	    private int endRow;
	    //总记录数
//	    private long total;
	    //总页数
	    private int pages;
	    //结果集
	    private List<T> list;
	 
	    //前一页
	    private int prePage;
	    //下一页
	    private int nextPage;
	 
	    //是否为第一页
//	    private boolean isFirstPage = false;
	    //是否为最后一页
//	    private boolean isLastPage = false;
	    //是否有前一页
	    private boolean hasPreviousPage = false;
	    //是否有下一页
	    private boolean hasNextPage = false;
	    //导航页码数
	    private int navigatePages;
	    //所有导航页号
	    private int[] navigatepageNums;
	    //导航条上的第一页
//	    private int navigateFirstPage;
	    //导航条上的最后一页
//	    private int navigateLastPage;
	 
	    public PageInfo() {
	    }
	 
	    /**
	     * 包装Page对象
	     *
	     * @param list
	     */
	    public PageInfo(List<T> list) {
	        this(list, 8);
	    }
	 
	    /**
	     * 包装Page对象
	     *
	     * @param list          page结果
	     * @param navigatePages 页码数量
	     */
	    public PageInfo(List<T> list, int navigatePages) {
	        if (list instanceof Page) {
	            Page page = (Page) list;
	            this.pageNum = page.getPageNum();
	            this.pageSize = page.getPageSize();
	 
	            this.pages = page.getPages();
	            this.list = page;
	            this.size = page.size();
	            //由于结果是>startRow的，所以实际的需要+1
	        } else if (list instanceof Collection) {
	            this.pageNum = 1;
	            this.pageSize = list.size();
	 
	            this.pages = this.pageSize > 0 ? 1 : 0;
	            this.list = list;
	            this.size = list.size();
	        }
	        if (list instanceof Collection) {
	            this.navigatePages = navigatePages;
	            //计算导航页
//	            calcNavigatepageNums();
	            //计算前后页，第一页，最后一页
//	            calcPage();
	            //判断页面边界
//	            judgePageBo
//	            udary();
	        }
	    }
	 
	 
	}

	 	
