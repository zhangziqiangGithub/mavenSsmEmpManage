package com.service;

import java.util.List;

import com.po.Dep;

public interface IDepService {
	//��ѯ����������Ϣ
public List<Dep> findall();
//�������
public boolean save(Dep dep);
//�鵥����
public Dep findbyid(int depid);
//�޸Ĳ���
public boolean update(Dep dep);
}
