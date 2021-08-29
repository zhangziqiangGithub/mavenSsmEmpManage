package com.service;

import java.util.List;

import com.po.Dep;

public interface IDepService {
	//查询部门所有信息
public List<Dep> findall();
//部门添加
public boolean save(Dep dep);
//查单部门
public Dep findbyid(int depid);
//修改部门
public boolean update(Dep dep);
}
