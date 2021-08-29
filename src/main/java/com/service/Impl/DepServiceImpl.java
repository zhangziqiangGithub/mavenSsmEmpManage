package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Dep;
import com.service.IDepService;
import com.util.DaoServiceUtil;

@Service("depbiz")
@Transactional
public class DepServiceImpl implements IDepService {
@Resource(name="daoservice")
private DaoServiceUtil daoservice;

	public DaoServiceUtil getDaoservice() {
	return daoservice;
}

public void setDaoservice(DaoServiceUtil daoservice) {
	this.daoservice = daoservice;
}

	@Override
	public List<Dep> findall() {
		// TODO Auto-generated method stub
		return daoservice.getDepdao().findall();
	}

	@Override
	public boolean save(Dep dep) {
		int code=daoservice.getDepdao().save(dep);
		if(code>0){
			return true;
		}
		return false;
	}

	@Override
	public Dep findbyid(int depid) {
		// TODO Auto-generated method stub
		return daoservice.getDepdao().findbyid(depid);
	}

	@Override
	public boolean update(Dep dep) {
		int code=daoservice.getDepdao().update(dep);
		if(code>0){
			return true;
		}
		return false;
	}



}
