package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;

public interface IEmpService {
	
public boolean save(Emp emp);
	//��ҳ��ѯ
	public List<Emp> findPageall(PageBean pb);
	//�ܼ�¼����ѯ
	public int findmaxRows();
	//ɾ��
	public boolean delByid(Integer eid);
	//��ѯ����
	public Emp findByid(Integer eid);
	//�޸�
	public boolean update(Emp emp);
}
