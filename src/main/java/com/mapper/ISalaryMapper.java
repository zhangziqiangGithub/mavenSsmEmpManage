package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.Salary;
@Service("salarydao")
public interface ISalaryMapper {//薪资添加
	public int sava(Salary salary);
	//通过员工编号删除员工薪资
	public int delByeid(int eid);
	//通过员工编号查询员工薪资
	public Salary findByeid(int eid);
	//通过员工编号修改员工薪资
	public int update(Salary sa);
}
