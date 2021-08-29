package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Dep;

@Service("Depdao")
public interface IDepMapper {
	//��ѯ����������Ϣ
public List<Dep> findall();
//�������
public int save(Dep dep);
//�鵥����
public Dep findbyid(int depid);
//�޸Ĳ���
public int update(Dep dep);
}
