package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;
import com.po.Welfare;
import com.util.AJAXUtil;
import com.util.BizServiceUtil;

@Controller
public class EmpController {
@Resource(name="bizservice")
private BizServiceUtil bizservice;

public BizServiceUtil getBizserivice() {
	return bizservice;
}

public void setBizserivice(BizServiceUtil bizserivice) {
	this.bizservice = bizserivice;
}
/****���ҳ���ʼ��(���ز��ź͸���)****/
@RequestMapping(value="doinit_emp.do")
public String doinit(HttpServletRequest request,HttpServletResponse response){
	Map<String, Object> map=new HashMap<String, Object>();
	List<Dep> lsdep=bizservice.getDepservice().findall();
	List<Welfare> lswf=bizservice.getWelfareservice().findall();
	map.put("lsdep", lsdep);
	map.put("lswf", lswf);
	//���˲���Ҫ������
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//תJSON�ַ���
	String jsonemp=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON�ַ�����"+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****��ѯ����****/
@RequestMapping(value="finddep_emp.do")
public String finddep(HttpServletRequest request,HttpServletResponse response){

	List<Dep> lsdep=bizservice.getDepservice().findall();
	
	//���˲���Ҫ������
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//תJSON�ַ���
	String jsonemp=JSONObject.toJSONString(lsdep,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON�ַ�����"+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****��ѯ���ŵ���****/
@RequestMapping(value="findbyiddep_dep.do")
public String findbyiddep(HttpServletRequest request,HttpServletResponse response,Integer depid){
System.out.println("Ҫ��ѯ��ID"+depid);
	Dep olddep=bizservice.getDepservice().findbyid(depid);
	
	//���˲���Ҫ������
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//תJSON�ַ���
	String jsonemp=JSONObject.toJSONString(olddep,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON�ַ�����"+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****�������****/
@RequestMapping(value="savedep_emp.do")
public String savedep(HttpServletRequest request,HttpServletResponse response, Dep dep){
System.out.println(dep.getDepname());
	boolean flag=bizservice.getDepservice().save(dep);
	System.out.println("Ա����Ϣ��"+dep.toString());
	if(flag){
	AJAXUtil.printString(response, "1");
	}else{
		AJAXUtil.printString(response, "0");
	}
	
	return null;
	
}

/****�����޸�****/
@RequestMapping(value="updatedep_emp.do")
public String updatedep(HttpServletRequest request,HttpServletResponse response,Dep dep){
	System.out.println("Ҫ�޸ĵļ�¼id:"+dep.getDepid());
	
	boolean flag=bizservice.getDepservice().update(dep);
	if(flag){
		AJAXUtil.printString(response, "1");
		}else{
			AJAXUtil.printString(response, "0");
		}
	return null;
	
}

/****��ѯ����(�޸���)****/
@RequestMapping(value="findByid_emp.do")
public String findByid(HttpServletRequest request,HttpServletResponse response,Integer eid){
	System.out.println("Ҫ��ѯ�ļ�¼id:"+eid);
	Emp oldemp=bizservice.getEmpservice().findByid(eid);
	//���˲���Ҫ������
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//תJSON�ַ���
	String jsonemp=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON�ַ�����"+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****�޸�****/
@RequestMapping(value="update_emp.do")
public String update(HttpServletRequest request,HttpServletResponse response,Emp emp){
	System.out.println("Ҫ�޸ĵļ�¼id:"+emp.getEid());
	
	boolean flag=bizservice.getEmpservice().update(emp);
	if(flag){
		AJAXUtil.printString(response, "1");
		}else{
			AJAXUtil.printString(response, "0");
		}
	return null;
	
}
/****��ѯ����(������)****/
@RequestMapping(value="findddByid_emp.do")
public String findddByid(HttpServletRequest request,HttpServletResponse response,Integer eid){
	System.out.println("Ҫ��ѯ�ļ�¼id:"+eid);
	Emp oldemp=bizservice.getEmpservice().findByid(eid);
	//���˲���Ҫ������
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//תJSON�ַ���
	String jsonemp=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON�ַ�����"+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}
/****ɾ��****/
@RequestMapping(value="delByid_emp.do")
public String delByid(HttpServletRequest request,HttpServletResponse response,Integer eid){
	System.out.println("Ҫɾ���ļ�¼id:"+eid);
	boolean flag=bizservice.getEmpservice().delByid(eid);
	if(flag){
		AJAXUtil.printString(response, "1");
		}else{
			AJAXUtil.printString(response, "0");
		}
	return null;
	
}

/****��ҳ��ѯ****/
@RequestMapping(value="findPageAll_emp.do")
public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
	System.out.println("��ҳ��ѯִ�У���������"+page+"---"+rows);
	//��Ϊezui��ҳ���������ǹ̶��ĸ��Ӹ�ʽ������Ҫ�����ݴ���MAP�������ٷ��ظ�ǰ̨
	Map<String, Object> map=new HashMap<String, Object>();
	PageBean pb=new PageBean();
	page=page==null||page<1?pb.getPage():page;
	rows=rows==null||rows<1?pb.getRows():rows;
	if(rows>20)rows=20;
	pb.setPage(page);
	pb.setRows(rows);
	List<Emp> lsemp=bizservice.getEmpservice().findPageall(pb);
	int maxrows=bizservice.getEmpservice().findmaxRows();
	//ƴ��ǰ̨ezui��ҳ��Ҫ������(�̶�)
	map.put("page", page);//��ǰҳ
	map.put("rows", lsemp);//��ҳ��Ҫ�����ݣ���¼������
	map.put("total", maxrows);//�ܼ�¼��
	
	//���˲���Ҫ������
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//תJSON�ַ���
	String jsonemp=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON�ַ�����"+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****���****/
@RequestMapping(value="save_emp.do")
public String save(HttpServletRequest request,HttpServletResponse response,Emp emp){
	//��ȡ��վ�ĸ�·��
		String realpath=request.getRealPath("/");
		System.out.println("��վ��·����"+realpath);
		/**********************�����ļ��ϴ�begin*************************/
		//��ȡ�ϴ���Ƭ�Ķ���
		MultipartFile multipartFile=emp.getPic();
		//�ж��������Ϊnull ��Ϊ��
		if(multipartFile!=null&&!multipartFile.isEmpty()){ 
			//��ȡ�ϴ��ļ�������
			String fname=multipartFile.getOriginalFilename();
			//����
			if(fname.lastIndexOf(".")!=-1){
				//��ȡ�ļ���׺
				String ext=fname.substring(fname.lastIndexOf("."));
				//������ļ���ȡ�����ĺ�׺  == �� .jpg
				if(ext.equalsIgnoreCase(".jpg")){
					//getTime()���ص���һ��long�͵ĺ�����
					String newfname=new Date().getTime()+ext;
					//�����ļ�����ָ���ļ�·��(��վ(Tomcat)��·����uppic�ļ��У��ļ���)
					File dostFile=new File(realpath+"/uppic/"+newfname);
					//�ϴ��������󴫵ݵ��ļ�����(multipartFile)����һ�ݵ��ղ����ɵ��ļ��У�
					try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostFile);
						//������֮����ļ����������
						emp.setPhopo(newfname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		/**********************�����ļ��ϴ�end*************************/
	boolean flag=bizservice.getEmpservice().save(emp);
	System.out.println("Ա����Ϣ��"+emp.toString());
	if(flag){
	AJAXUtil.printString(response, "1");
	}else{
		AJAXUtil.printString(response, "0");
	}
	return null;
	
}
}
