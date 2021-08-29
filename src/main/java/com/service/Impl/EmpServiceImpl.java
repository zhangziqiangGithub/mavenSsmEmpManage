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
		//����Ա�����
		int code=daoservice.getEmpdao().sava(emp);
		//�����Ա��н�����
		if(code>0){
			//��ȡ��Ա���ı��
			int eid=daoservice.getEmpdao().findmaxeid();
			/*****н�ʵı���begin*****/
			Salary sa=new Salary(eid,emp.getEmoney());
			daoservice.getSalarydao().sava(sa);
			/*****н�ʵı���end*****/
			
			/****������ӱ���begin****/
			//��ȡԱ�������ı������
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf=new EmpWelfare(eid,Integer.parseInt(wids[i]));
					daoservice.getEmpwelfaredao().sava(ewf);
				}
			}
			/****������ӱ���end****/
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
		//��Ϊ���ӱ�Ĺ�ϵ����Ҫ����˳��ɾ����Ҫɾ������ɾ�ӱ�
		  //ɾн��
		daoservice.getSalarydao().delByeid(eid);
		  //ɾ������ϵ��
		daoservice.getEmpwelfaredao().delByeid(eid);
		//ɾ��Ա��
		int code =daoservice.getEmpdao().delByid(eid);
		if(code>0){
			System.out.println("serviceimplɾ��ִ�гɹ���������");
			return true;
		}
		return false;
	}

	@Override
	public Emp findByid(Integer eid) {
		//��ȡԱ������
		Emp oldemp=daoservice.getEmpdao().findByid(eid);
		//��ȡн��
		Salary sa=daoservice.getSalarydao().findByeid(eid);
		if(sa!=null&&sa.getEmoney()!=null){
			oldemp.setEmoney(sa.getEmoney());
		}
		//��ȡ����
		List<Welfare> lswf=daoservice.getEmpwelfaredao().findByeid(eid);
		  System.out.println("aaa"+lswf.size());
		//������id
		   String[] wids=new String[lswf.size()];
		   for (int i = 0; i < wids.length; i++) {
			Welfare wf=lswf.get(i);
			wids[i]=wf.getWid().toString();
		}
		   System.out.println("impl��������:"+lswf.toString());
		oldemp.setLswf(lswf);
		oldemp.setWids(wids);
		System.out.println("implԱ��:"+oldemp.toString());
		return oldemp;
	}

	@Override
	public boolean update(Emp emp) {
		//�޸�Ա��
		int code=daoservice.getEmpdao().update(emp);
		if(code>0){
			//�޸�н��
			  //��ȡԭ����н�ʼ�¼
			Salary oldsa=daoservice.getSalarydao().findByeid(emp.getEid());
			  //�ж�ԭ���Ƿ���н��
			if(oldsa!=null&&oldsa.getEmoney()!=null){//˵��ԭ������н��
				//�޸�
				oldsa.setEmoney(emp.getEmoney());
				daoservice.getSalarydao().update(oldsa);
			}else{
				/*****н�ʵı���begin*****/
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoservice.getSalarydao().sava(sa);
			}
			//������
			  //��ȡԭ���ĸ���
			List<Welfare> lswf=daoservice.getEmpwelfaredao().findByeid(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				//ɾ��ԭ�еĸ���
				daoservice.getEmpwelfaredao().delByeid(emp.getEid());
			}
			//��Ӹ���
			/****������ӱ���begin****/
			//��ȡԱ�������ı������
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf=new EmpWelfare(emp.getEid(),Integer.parseInt(wids[i]));
					daoservice.getEmpwelfaredao().sava(ewf);
				}
			}
			/****������ӱ���end****/
			return true;
		}
		
		return false;
	}

}
