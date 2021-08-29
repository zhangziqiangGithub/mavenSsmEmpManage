package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.PageBean;
import com.po.Salary;
import com.po.Welfare;
import com.service.IEmpService;
import com.util.DaoServiceUtil;
@Service("empbiz")
@Transactional
public class EmpServiceImpl implements IEmpService {
@Resource(name="daoservice")
private DaoServiceUtil daoservice;

public DaoServiceUtil getDaoservice() {
return daoservice;
}

public void setDaoservice(DaoServiceUtil daoservice) {
this.daoservice = daoservice;
}
	@Override
	public boolean save(Emp emp) {
		//处理员工添加
		int code=daoservice.getEmpdao().sava(emp);
		//处理该员工薪资添加
		if(code>0){
			//获取该员工的编号
			int eid=daoservice.getEmpdao().findmaxeid();
			/*****薪资的保存begin*****/
			Salary sa=new Salary(eid,emp.getEmoney());
			daoservice.getSalarydao().sava(sa);
			/*****薪资的保存end*****/
			
			/****福利添加保存begin****/
			//获取员工福利的编号数组
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf=new EmpWelfare(eid,Integer.parseInt(wids[i]));
					daoservice.getEmpwelfaredao().sava(ewf);
				}
			}
			/****福利添加保存end****/
			return true;
		}
		return false;
	}

	@Override
	public List<Emp> findPageall(PageBean pb) {
		// TODO Auto-generated method stub
		return daoservice.getEmpdao().findPageall(pb.getPage(), pb.getRows());
	}

	@Override
	public int findmaxRows() {
		// TODO Auto-generated method stub
		return daoservice.getEmpdao().findmaxRows();
	}

	@Override
	public boolean delByid(Integer eid) {
		//因为主从表的关系，需要按照顺序删除（要删主表先删从表）
		  //删薪资
		daoservice.getSalarydao().delByeid(eid);
		  //删福利关系表
		daoservice.getEmpwelfaredao().delByeid(eid);
		//删除员工
		int code =daoservice.getEmpdao().delByid(eid);
		if(code>0){
			System.out.println("serviceimpl删除执行成功。。。。");
			return true;
		}
		return false;
	}

	@Override
	public Emp findByid(Integer eid) {
		//获取员工对象
		Emp oldemp=daoservice.getEmpdao().findByid(eid);
		//获取薪资
		Salary sa=daoservice.getSalarydao().findByeid(eid);
		if(sa!=null&&sa.getEmoney()!=null){
			oldemp.setEmoney(sa.getEmoney());
		}
		//获取福利
		List<Welfare> lswf=daoservice.getEmpwelfaredao().findByeid(eid);
		  System.out.println("aaa"+lswf.size());
		//处理福利id
		   String[] wids=new String[lswf.size()];
		   for (int i = 0; i < wids.length; i++) {
			Welfare wf=lswf.get(i);
			wids[i]=wf.getWid().toString();
		}
		   System.out.println("impl福利集合:"+lswf.toString());
		oldemp.setLswf(lswf);
		oldemp.setWids(wids);
		System.out.println("impl员工:"+oldemp.toString());
		return oldemp;
	}

	@Override
	public boolean update(Emp emp) {
		//修改员工
		int code=daoservice.getEmpdao().update(emp);
		if(code>0){
			//修改薪资
			  //获取原来的薪资记录
			Salary oldsa=daoservice.getSalarydao().findByeid(emp.getEid());
			  //判断原来是否有薪资
			if(oldsa!=null&&oldsa.getEmoney()!=null){//说明原对象有薪资
				//修改
				oldsa.setEmoney(emp.getEmoney());
				daoservice.getSalarydao().update(oldsa);
			}else{
				/*****薪资的保存begin*****/
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoservice.getSalarydao().sava(sa);
			}
			//处理福利
			  //获取原来的福利
			List<Welfare> lswf=daoservice.getEmpwelfaredao().findByeid(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				//删除原有的福利
				daoservice.getEmpwelfaredao().delByeid(emp.getEid());
			}
			//添加福利
			/****福利添加保存begin****/
			//获取员工福利的编号数组
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf=new EmpWelfare(emp.getEid(),Integer.parseInt(wids[i]));
					daoservice.getEmpwelfaredao().sava(ewf);
				}
			}
			/****福利添加保存end****/
			return true;
		}
		
		return false;
	}

}
