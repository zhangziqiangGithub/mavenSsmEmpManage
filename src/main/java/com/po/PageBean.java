package com.po;

import java.util.List;

public class PageBean {//·ÖÒ³
	private Integer page;
	private Integer rows;
	private Integer maxpage;
	private List<?> pagelist;
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(Integer page, Integer rows, Integer maxpage, List<?> pagelist) {
		super();
		this.page = page;
		this.rows = rows;
		this.maxpage = maxpage;
		this.pagelist = pagelist;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(Integer maxpage) {
		this.maxpage = maxpage;
	}
	public List<?> getPagelist() {
		return pagelist;
	}
	public void setPagelist(List<?> pagelist) {
		this.pagelist = pagelist;
	}
	@Override
	public String toString() {
		return "PageBean [page=" + page + ", rows=" + rows + ", maxpage=" + maxpage + ", pagelist=" + pagelist + "]";
	}

}
