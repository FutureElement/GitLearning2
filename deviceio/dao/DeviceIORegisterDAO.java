package com.jbns.asset.deviceio.dao;

import java.util.List;

import com.jbns.asset.deviceio.entity.DeviceIO;
import com.jbns.asset.deviceio.entity.SearchParameters;
import com.jbns.util.sql.DBInterface;

public interface DeviceIORegisterDAO extends DBInterface
{
	String	Name	= "DeviceIORegisterDAO";

	public void addDeviceRecord(DeviceIO dio) throws Exception;

	public void modifyDeviceRecord(DeviceIO dio) throws Exception;

	public void deleteDeviceRecord(String id) throws Exception;

	public DeviceIO findDeviceRecord(String id) throws Exception;

	public List<DeviceIO> searchDeviceRecords(SearchParameters pars) throws Exception;
	
	public int getMaxPage(int pageSize) throws Exception;
}
