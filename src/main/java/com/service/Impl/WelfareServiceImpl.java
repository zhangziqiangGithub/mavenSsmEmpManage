package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Welfare;
import com.service.IWelfareService;
import com.util.DaoServiceUtil;

@Service("welfarebiz")
@Transactional
public class WelfareServiceImpl implements IWelfareService {
@Resource(name="daoservice")
private DaoServiceUtil daoservice;

	public DaoServiceUtil getDaoservice() {
	return daoservice;
}

public void setDaoservice(DaoServiceUtil daoservice) {
	this.daoservice = daoservice;
}

@Override
public List<Welfare> findall() {
	// TODO Auto-generated method stub
	return daoservice.getWelfaredao().findall();
}

	

}
