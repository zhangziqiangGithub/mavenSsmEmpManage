package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.IDepMapper;
import com.mapper.IWelfareMapper;
import com.service.IDepService;
import com.service.IEmpService;
import com.service.IWelfareService;

@Service("bizservice")
public class BizServiceUtil {
@Resource(name="depbiz")
private IDepService depservice;
@Resource(name="welfarebiz")
private IWelfareService welfareservice;
@Resource(name="empbiz")
private IEmpService empservice;
public IDepService getDepservice() {
	return depservice;
}
public void setDepservice(IDepService depservice) {
	this.depservice = depservice;
}
public IWelfareService getWelfareservice() {
	return welfareservice;
}
public void setWelfareservice(IWelfareService welfareservice) {
	this.welfareservice = welfareservice;
}
public IEmpService getEmpservice() {
	return empservice;
}
public void setEmpservice(IEmpService empservice) {
	this.empservice = empservice;
}




}
