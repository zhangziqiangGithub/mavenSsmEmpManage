package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Dep;

@Service("Depdao")
public interface IDepMapper {
	//查询部门所有信息
public List<Dep> findall();
//部门添加
public int save(Dep dep);
//查单部门
public Dep findbyid(int depid);
//修改部门
public int update(Dep dep);
}
