package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.Salary;
import com.po.Welfare;
@Service("empwelfaredao")
public interface IEmpWelfareMapper {//员工福利添加
	public int sava(EmpWelfare empWelfare);
	//通过员工编号删除员工福利关系
	public Integer delByeid(int eid);
	//通过员工编号查询员工福利关系(直接返回福利的名称集合)
	public List<Welfare> findByeid(int eid);
}
