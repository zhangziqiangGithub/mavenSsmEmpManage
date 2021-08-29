package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.IDepMapper;
import com.mapper.IEmpMapper;
import com.mapper.IEmpWelfareMapper;
import com.mapper.ISalaryMapper;
import com.mapper.IWelfareMapper;

@Service("daoservice")
public class DaoServiceUtil {
@Resource(name="Depdao")
private IDepMapper depdao;
@Resource(name="welfareDao")
private IWelfareMapper welfaredao;
@Resource(name="empdao")
private IEmpMapper empdao;
@Resource(name="salarydao")
private ISalaryMapper salarydao;
@Resource(name="empwelfaredao")
private IEmpWelfareMapper empwelfaredao;

public IDepMapper getDepdao() {
	return depdao;
}
public void setDepdao(IDepMapper depdao) {
	this.depdao = depdao;
}
public IWelfareMapper getWelfaredao() {
	return welfaredao;
}
public void setWelfaredao(IWelfareMapper welfaredao) {
	this.welfaredao = welfaredao;
}
public IEmpMapper getEmpdao() {
	return empdao;
}
public void setEmpdao(IEmpMapper empdao) {
	this.empdao = empdao;
}
public ISalaryMapper getSalarydao() {
	return salarydao;
}
public void setSalarydao(ISalaryMapper salarydao) {
	this.salarydao = salarydao;
}
public IEmpWelfareMapper getEmpwelfaredao() {
	return empwelfaredao;
}
public void setEmpwelfaredao(IEmpWelfareMapper empwelfaredao) {
	this.empwelfaredao = empwelfaredao;
}




}
