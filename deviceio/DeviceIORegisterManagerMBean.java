package com.jbns.asset.deviceio;

import java.util.List;

import javax.management.NotificationEmitter;

import com.jbns.asset.deviceio.entity.DeviceIO;
import com.jbns.asset.deviceio.entity.SearchParameters;
import com.jbns.core.kernel.JService;

public interface DeviceIORegisterManagerMBean extends JService, NotificationEmitter
{
	String	NAME	= "DeviceIORegisterManager";

	public void addDeviceRecord(DeviceIO dio) throws Exception;

	public void modifyDeviceRecord(DeviceIO dio) throws Exception;

	public void deleteDeviceRecord(String id) throws Exception;

	public DeviceIO findDeviceRecord(String id) throws Exception;

	public List<DeviceIO> searchDeviceRecords(SearchParameters pars) throws Exception;

	public int getMaxPage(int pageSize) throws Exception;
}
