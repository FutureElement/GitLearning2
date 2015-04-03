package com.jbns.asset.deviceio.entity;

import java.io.Serializable;
import java.sql.Date;

public class DeviceIO implements Serializable
{
	private static final long	serialVersionUID	= 3061855843577621657L;

	private String				id;										// 主键
	private String				devicemodel;								// 设备型号
	private String				deviceindex;								// 设备序号
	private String				department;								// 单位
	private String				contact;									// 联系人
	private String				contactnumber;								// 联系电话
	private String				cabinetnumber;								// 机柜号
	private String				cabinetposition;							// 机柜位置
	private String				handler;									// 经手人
	private Date				handletime;								// 经手时间
	private String				remarks;									// 备注
	private String				devicestate;								// 设备状态

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDevicemodel()
	{
		return devicemodel;
	}

	public void setDevicemodel(String devicemodel)
	{
		this.devicemodel = devicemodel;
	}

	public String getDeviceindex()
	{
		return deviceindex;
	}

	public void setDeviceindex(String deviceindex)
	{
		this.deviceindex = deviceindex;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	public String getContactnumber()
	{
		return contactnumber;
	}

	public void setContactnumber(String contactnumber)
	{
		this.contactnumber = contactnumber;
	}

	public String getCabinetnumber()
	{
		return cabinetnumber;
	}

	public void setCabinetnumber(String cabinetnumber)
	{
		this.cabinetnumber = cabinetnumber;
	}

	public String getCabinetposition()
	{
		return cabinetposition;
	}

	public void setCabinetposition(String cabinetposition)
	{
		this.cabinetposition = cabinetposition;
	}

	public String getHandler()
	{
		return handler;
	}

	public void setHandler(String handler)
	{
		this.handler = handler;
	}

	public Date getHandletime()
	{
		return handletime;
	}

	public void setHandletime(Date handletime)
	{
		this.handletime = handletime;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getDevicestate()
	{
		return devicestate;
	}

	public void setDevicestate(String devicestate)
	{
		this.devicestate = devicestate;
	}

}
