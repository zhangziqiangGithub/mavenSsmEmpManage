package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.Salary;
@Service("salarydao")
public interface ISalaryMapper {//н�����
	public int sava(Salary salary);
	//ͨ��Ա�����ɾ��Ա��н��
	public int delByeid(int eid);
	//ͨ��Ա����Ų�ѯԱ��н��
	public Salary findByeid(int eid);
	//ͨ��Ա������޸�Ա��н��
	public int update(Salary sa);
}
