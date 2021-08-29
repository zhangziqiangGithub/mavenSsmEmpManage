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
/****添加页面初始化(返回部门和福利)****/
@RequestMapping(value="doinit_emp.do")
public String doinit(HttpServletRequest request,HttpServletResponse response){
	Map<String, Object> map=new HashMap<String, Object>();
	List<Dep> lsdep=bizservice.getDepservice().findall();
	List<Welfare> lswf=bizservice.getWelfareservice().findall();
	map.put("lsdep", lsdep);
	map.put("lswf", lswf);
	//过滤不需要的属性
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//转JSON字符串
	String jsonemp=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON字符串："+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****查询部门****/
@RequestMapping(value="finddep_emp.do")
public String finddep(HttpServletRequest request,HttpServletResponse response){

	List<Dep> lsdep=bizservice.getDepservice().findall();
	
	//过滤不需要的属性
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//转JSON字符串
	String jsonemp=JSONObject.toJSONString(lsdep,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON字符串："+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****查询部门单个****/
@RequestMapping(value="findbyiddep_dep.do")
public String findbyiddep(HttpServletRequest request,HttpServletResponse response,Integer depid){
System.out.println("要查询的ID"+depid);
	Dep olddep=bizservice.getDepservice().findbyid(depid);
	
	//过滤不需要的属性
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//转JSON字符串
	String jsonemp=JSONObject.toJSONString(olddep,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON字符串："+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****部门添加****/
@RequestMapping(value="savedep_emp.do")
public String savedep(HttpServletRequest request,HttpServletResponse response, Dep dep){
System.out.println(dep.getDepname());
	boolean flag=bizservice.getDepservice().save(dep);
	System.out.println("员工信息："+dep.toString());
	if(flag){
	AJAXUtil.printString(response, "1");
	}else{
		AJAXUtil.printString(response, "0");
	}
	
	return null;
	
}

/****部门修改****/
@RequestMapping(value="updatedep_emp.do")
public String updatedep(HttpServletRequest request,HttpServletResponse response,Dep dep){
	System.out.println("要修改的记录id:"+dep.getDepid());
	
	boolean flag=bizservice.getDepservice().update(dep);
	if(flag){
		AJAXUtil.printString(response, "1");
		}else{
			AJAXUtil.printString(response, "0");
		}
	return null;
	
}

/****查询单个(修改用)****/
@RequestMapping(value="findByid_emp.do")
public String findByid(HttpServletRequest request,HttpServletResponse response,Integer eid){
	System.out.println("要查询的记录id:"+eid);
	Emp oldemp=bizservice.getEmpservice().findByid(eid);
	//过滤不需要的属性
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//转JSON字符串
	String jsonemp=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON字符串："+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****修改****/
@RequestMapping(value="update_emp.do")
public String update(HttpServletRequest request,HttpServletResponse response,Emp emp){
	System.out.println("要修改的记录id:"+emp.getEid());
	
	boolean flag=bizservice.getEmpservice().update(emp);
	if(flag){
		AJAXUtil.printString(response, "1");
		}else{
			AJAXUtil.printString(response, "0");
		}
	return null;
	
}
/****查询单个(详情用)****/
@RequestMapping(value="findddByid_emp.do")
public String findddByid(HttpServletRequest request,HttpServletResponse response,Integer eid){
	System.out.println("要查询的记录id:"+eid);
	Emp oldemp=bizservice.getEmpservice().findByid(eid);
	//过滤不需要的属性
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//转JSON字符串
	String jsonemp=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON字符串："+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}
/****删除****/
@RequestMapping(value="delByid_emp.do")
public String delByid(HttpServletRequest request,HttpServletResponse response,Integer eid){
	System.out.println("要删除的记录id:"+eid);
	boolean flag=bizservice.getEmpservice().delByid(eid);
	if(flag){
		AJAXUtil.printString(response, "1");
		}else{
			AJAXUtil.printString(response, "0");
		}
	return null;
	
}

/****分页查询****/
@RequestMapping(value="findPageAll_emp.do")
public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
	System.out.println("分页查询执行：。。。。"+page+"---"+rows);
	//因为ezui分页接受数据是固定的复杂格式，所以要将数据存在MAP集合中再返回给前台
	Map<String, Object> map=new HashMap<String, Object>();
	PageBean pb=new PageBean();
	page=page==null||page<1?pb.getPage():page;
	rows=rows==null||rows<1?pb.getRows():rows;
	if(rows>20)rows=20;
	pb.setPage(page);
	pb.setRows(rows);
	List<Emp> lsemp=bizservice.getEmpservice().findPageall(pb);
	int maxrows=bizservice.getEmpservice().findmaxRows();
	//拼接前台ezui分页需要的数据(固定)
	map.put("page", page);//当前页
	map.put("rows", lsemp);//分页需要的数据（记录）集合
	map.put("total", maxrows);//总记录数
	
	//过滤不需要的属性
	PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
	//转JSON字符串
	String jsonemp=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	System.out.println("JSON字符串："+jsonemp);
	AJAXUtil.printString(response, jsonemp);
	return null;
	
}

/****添加****/
@RequestMapping(value="save_emp.do")
public String save(HttpServletRequest request,HttpServletResponse response,Emp emp){
	//获取网站的根路径
		String realpath=request.getRealPath("/");
		System.out.println("网站根路径："+realpath);
		/**********************处理文件上传begin*************************/
		//获取上传照片的对象
		MultipartFile multipartFile=emp.getPic();
		//判断这个对象不为null 不为空
		if(multipartFile!=null&&!multipartFile.isEmpty()){ 
			//获取上传文件的名称
			String fname=multipartFile.getOriginalFilename();
			//改名
			if(fname.lastIndexOf(".")!=-1){
				//截取文件后缀
				String ext=fname.substring(fname.lastIndexOf("."));
				//如果从文件截取下来的后缀  == 于 .jpg
				if(ext.equalsIgnoreCase(".jpg")){
					//getTime()返回的是一个long型的毫秒数
					String newfname=new Date().getTime()+ext;
					//创建文件对象，指定文件路径(网站(Tomcat)根路径，uppic文件夹，文件名)
					File dostFile=new File(realpath+"/uppic/"+newfname);
					//上传（将请求传递的文件内容(multipartFile)复制一份到刚才生成的文件中）
					try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostFile);
						//将处理之后的文件名存入对象
						emp.setPhopo(newfname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		/**********************处理文件上传end*************************/
	boolean flag=bizservice.getEmpservice().save(emp);
	System.out.println("员工信息："+emp.toString());
	if(flag){
	AJAXUtil.printString(response, "1");
	}else{
		AJAXUtil.printString(response, "0");
	}
	return null;
	
}
}
