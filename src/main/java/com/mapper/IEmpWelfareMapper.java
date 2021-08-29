package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.Salary;
import com.po.Welfare;
@Service("empwelfaredao")
public interface IEmpWelfareMapper {//Ա���������
	public int sava(EmpWelfare empWelfare);
	//ͨ��Ա�����ɾ��Ա��������ϵ
	public Integer delByeid(int eid);
	//ͨ��Ա����Ų�ѯԱ��������ϵ(ֱ�ӷ��ظ��������Ƽ���)
	public List<Welfare> findByeid(int eid);
}
