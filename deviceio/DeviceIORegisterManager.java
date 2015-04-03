package com.jbns.asset.deviceio;

import java.util.List;

import javax.management.ListenerNotFoundException;
import javax.management.MBeanNotificationInfo;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

import com.jbns.asset.AccountNumberManager;
import com.jbns.asset.deviceio.dao.DeviceIORegisterDAO;
import com.jbns.asset.deviceio.entity.DeviceIO;
import com.jbns.asset.deviceio.entity.SearchParameters;
import com.jbns.core.exception.StartMBeanFailedException;
import com.jbns.core.kernel.JMXKernel;
import com.jbns.core.kernel.JNotificationBroadcasterSupport;
import com.jbns.util.logging.Logger;
import com.jbns.util.logging.LoggerManager;

public class DeviceIORegisterManager implements DeviceIORegisterManagerMBean
{
	private Logger							logger	= LoggerManager
															.getLogger(AccountNumberManager.class);
	private JMXKernel						kernel;
	private DeviceIORegisterDAO				dao;
	private JNotificationBroadcasterSupport	support;

	@Override
	public void addDeviceRecord(DeviceIO dio) throws Exception
	{
		dao.addDeviceRecord(dio);
	}

	@Override
	public void modifyDeviceRecord(DeviceIO dio) throws Exception
	{
		dao.modifyDeviceRecord(dio);
	}

	@Override
	public void deleteDeviceRecord(String id) throws Exception
	{
		dao.deleteDeviceRecord(id);
	}

	@Override
	public DeviceIO findDeviceRecord(String id) throws Exception
	{
		return dao.findDeviceRecord(id);
	}

	@Override
	public List<DeviceIO> searchDeviceRecords(SearchParameters pars) throws Exception
	{
		return dao.searchDeviceRecords(pars);
	}

	@Override
	public int getMaxPage(int pageSize) throws Exception
	{
		return dao.getMaxPage(pageSize);
	}

	@Override
	public boolean isActive()
	{
		return kernel != null && dao != null;
	}

	@Override
	public void startService(JMXKernel kernel) throws StartMBeanFailedException
	{
		logger.info("START DeviceIORegisterManagerMBean");
		this.kernel = kernel;
		if (null != kernel)
		{
			try
			{
				dao = (DeviceIORegisterDAO) kernel.getDatabaseManager().getDBInterface(
						DeviceIORegisterDAO.Name);
			}
			catch (Exception e)
			{
				throw new StartMBeanFailedException(e);
			}
		}
		else
		{
			throw new StartMBeanFailedException("get kernel is null");
		}
	}

	@Override
	public void stopService()
	{
		logger.info("STOP DeviceIORegisterManagerMBean");
		kernel = null;
	}

	@Override
	public void removeNotificationListener(NotificationListener listener,
			NotificationFilter filter, Object handback) throws ListenerNotFoundException
	{
		support.removeNotificationListener(listener, filter, handback);

	}

	@Override
	public void addNotificationListener(NotificationListener listener, NotificationFilter filter,
			Object handback) throws IllegalArgumentException
	{
		support.addNotificationListener(listener, filter, handback);
	}

	@Override
	public MBeanNotificationInfo[] getNotificationInfo()
	{
		return support.getNotificationInfo();
	}

	@Override
	public void removeNotificationListener(NotificationListener listener)
			throws ListenerNotFoundException
	{
		support.removeNotificationListener(listener);
	}

}
