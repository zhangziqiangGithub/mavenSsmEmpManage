package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.po.Emp;
@Service("empdao")
public interface IEmpMapper {//Ա�����
	public int sava(Emp emp);
	//��ȡԱ�����ı��
	public int findmaxeid();
	//��ҳ��ѯ
	public List<Emp> findPageall(@Param("page") Integer page, @Param("rows") Integer rows);
	//�ܼ�¼����ѯ
	public int findmaxRows();
	//ɾ��
	public int delByid(int eid);
	//�鵥
	public Emp findByid(int eid);
	//�޸�
	public int update(Emp emp);
}
