package com.po;

import java.io.Serializable;

public class Salary implements Serializable {//н�ʱ�
	private Integer sid;//н��id
	private Integer eid;//н�ʶ�Ӧ��Ա��id
	private Float emoney;//н��
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	//���ר���в�
	public Salary(Integer eid, Float emoney) {
		super();
		this.eid = eid;
		this.emoney = emoney;
	}
	public Salary(Integer sid, Integer eid, Float emoney) {
		super();
		this.sid = sid;
		this.eid = eid;
		this.emoney = emoney;
	}
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Float getEmoney() {
		return emoney;
	}
	public void setEmoney(Float emoney) {
		this.emoney = emoney;
	}
	@Override
	public String toString() {
		return "Salary [sid=" + sid + ", eid=" + eid + ", emoney=" + emoney + "]";
	}
	

}
