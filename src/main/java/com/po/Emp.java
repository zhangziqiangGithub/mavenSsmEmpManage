package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Emp implements Serializable {//Ա����
	private Integer eid;
	private String ename;
	private String sex;
	private String address;
	private Date birthday;
	private String phopo="default.jpg";
	private Integer depid;
//����ҳ����ɵ���ʱ����
	private String depname;//��������
	private Float emoney;//Ա��н��
	
	//������ص�����
	private String[] wids;//�����������
	private List<Welfare> lswf;//��ȡ���ĸ�������
	//�ļ��ϴ�
	private MultipartFile pic;
	//����ת��
	private String sdate;
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp(Integer eid, String ename, String sex, String address, Date birthday, String phopo, Integer depid,
			String depname, Float emoney, String[] wids, List<Welfare> lswf, MultipartFile pic, String sdate) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.phopo = phopo;
		this.depid = depid;
		this.depname = depname;
		this.emoney = emoney;
		this.wids = wids;
		this.lswf = lswf;
		this.pic = pic;
		this.sdate = sdate;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhopo() {
		return phopo;
	}
	public void setPhopo(String phopo) {
		this.phopo = phopo;
	}
	public Integer getDepid() {
		return depid;
	}
	public void setDepid(Integer depid) {
		this.depid = depid;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public Float getEmoney() {
		return emoney;
	}
	public void setEmoney(Float emoney) {
		this.emoney = emoney;
	}
	public String[] getWids() {
		return wids;
	}
	public void setWids(String[] wids) {
		this.wids = wids;
	}
	public List<Welfare> getLswf() {
		return lswf;
	}
	public void setLswf(List<Welfare> lswf) {
		this.lswf = lswf;
	}
	public MultipartFile getPic() {
		return pic;
	}
	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}
	public String getSdate() {
		if(birthday!=null){
			sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		}
		return sdate;
	}
	public void setSdate(String sdate) {
		if(sdate!=null){
			try {
				birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.sdate = sdate;
	}
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", address=" + address + ", birthday="
				+ birthday + ", phopo=" + phopo + ", depid=" + depid + ", depname=" + depname + ", emoney=" + emoney
				+ ", wids=" + Arrays.toString(wids) + ", lswf=" + lswf + ", pic=" + pic + ", sdate=" + sdate + "]";
	}
	
	
}
