package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Welfare;

@Service("welfareDao")
public interface IWelfareMapper {
//��ѯ��������
	public List<Welfare> findall();
}
