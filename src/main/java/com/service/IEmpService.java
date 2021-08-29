package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;

public interface IEmpService {
	
public boolean save(Emp emp);
	//分页查询
	public List<Emp> findPageall(PageBean pb);
	//总记录数查询
	public int findmaxRows();
	//删除
	public boolean delByid(Integer eid);
	//查询单个
	public Emp findByid(Integer eid);
	//修改
	public boolean update(Emp emp);
}
