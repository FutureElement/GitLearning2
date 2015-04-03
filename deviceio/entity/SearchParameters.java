package com.jbns.asset.deviceio.entity;

import java.io.Serializable;
import java.util.Date;

import com.jbns.util.logging.LoggerManager;

public class SearchParameters implements Serializable
{
	private static final long	serialVersionUID	= 7408326261400662941L;

	private Integer				pageNum				= 1;
	private Integer				pageSize			= 12;

	private String				handler				= null;				// 经手人
	private String				devicestate			= null;				// 设备状态
	private String				department			= null;				// 单位
	private Date				i_handlertime1		= null;				// 经手时间
	private Date				i_handlertime2		= null;
	private Date				o_handlertime1		= null;
	private Date				o_handlertime2		= null;

	public Integer getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(Integer pageNum)
	{
		this.pageNum = pageNum;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public String getHandler()
	{
		return handler;
	}

	public void setHandler(String handler)
	{
		this.handler = handler;
	}

	public String getDevicestate()
	{
		return devicestate;
	}

	public void setDevicestate(String devicestate)
	{
		this.devicestate = devicestate;
	}

	public Date getI_handlertime1()
	{
		return i_handlertime1;
	}

	public void setI_handlertime1(Date iHandlertime1)
	{
		i_handlertime1 = iHandlertime1;
	}

	public Date getI_handlertime2()
	{
		return i_handlertime2;
	}

	public void setI_handlertime2(Date iHandlertime2)
	{
		i_handlertime2 = iHandlertime2;
	}

	public Date getO_handlertime1()
	{
		return o_handlertime1;
	}

	public void setO_handlertime1(Date oHandlertime1)
	{
		o_handlertime1 = oHandlertime1;
	}

	public Date getO_handlertime2()
	{
		return o_handlertime2;
	}

	public void setO_handlertime2(Date oHandlertime2)
	{
		o_handlertime2 = oHandlertime2;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getParametersSQL()
	{
		StringBuffer buff = new StringBuffer();
		try
		{
			if (null != devicestate || !devicestate.trim().equals(""))
			{
				buff.append(" and devicestate ='");
				buff.append(devicestate);
				buff.append("' ");
			}
			if (null != department || !department.trim().equals(""))
			{
				buff.append(" and devicestate like '%");
				buff.append(department);
				buff.append("%' ");
			}
			if (null != i_handlertime1)
			{
				buff.append(" and devicestate='进入' and handlertime >=");
				buff.append(i_handlertime1);
			}
			if (null != i_handlertime2)
			{
				buff.append(" and devicestate='进入' and handlertime <=");
				buff.append(i_handlertime2);
			}
			if (null != o_handlertime1)
			{
				buff.append(" and devicestate='离开' and handlertime >=");
				buff.append(o_handlertime1);
			}
			if (null != o_handlertime2)
			{
				buff.append(" and devicestate='离开' and handlertime <=");
				buff.append(o_handlertime2);
			}
		}
		catch (Exception e)
		{
			LoggerManager.getLogger(SearchParameters.class).error("getParametersSQL error");
			return null;
		}
		return buff.toString();
	}

	public int getStartNumber()
	{
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 12 : pageSize;
		return (pageNum - 1) * pageSize + 1;
	}

	public int getEndNumber()
	{
		return getStartNumber() + pageSize;
	}
}
