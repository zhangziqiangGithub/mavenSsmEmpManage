package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Welfare;

@Service("welfareDao")
public interface IWelfareMapper {
//查询弗里所有
	public List<Welfare> findall();
}
