package com.jbns.asset.deviceio.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.jbns.asset.deviceio.dao.DeviceIORegisterDAO;
import com.jbns.asset.deviceio.entity.DeviceIO;
import com.jbns.asset.deviceio.entity.SearchParameters;
import com.jbns.asset.util.Constants;
import com.jbns.util.logging.Logger;
import com.jbns.util.logging.LoggerManager;
import com.jbns.util.sql.DBAccess;
import com.jbns.util.sql.SQLSources;

public class DeviceIORegisterDBAcess extends DBAccess implements DeviceIORegisterDAO
{
	protected Logger		logger;
	protected SQLSources	sql	= new SQLSources("database", "DeviceIORegisterDBAcess");

	protected DeviceIORegisterDBAcess(String product) throws Exception
	{
		super(product);
		logger = LoggerManager.getLogger(DeviceIORegisterDBAcess.class);
		sql.setLogger(logger);
		try
		{
			sql.loadXML();
		}
		catch (Exception e)
		{
			logger.fatal("init sql sources", e);
			throw e;
		}
	}

	@Override
	public void deleteDeviceRecord(String id) throws Exception
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = getConnection();
			ps = conn.prepareStatement(sql.getSQL("deleteDeviceRecord"));
			ps.setString(NUM_1, id);
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.fatal("DeviceIORegisterDBAcess.deleteDeviceRecord.failure", e);
			throw e;
		}
		finally
		{
			closeDao(conn, ps, null);
		}
	}

	@Override
	public void modifyDeviceRecord(DeviceIO dio) throws Exception
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = getConnection();
			ps = conn.prepareStatement(sql.getSQL("addDeviceRecord"));
			ps.setString(NUM_1, dio.getId());
			ps.setString(NUM_2, dio.getDevicemodel());
			ps.setString(NUM_3, dio.getDeviceindex());
			ps.setString(NUM_4, dio.getDepartment());
			ps.setString(NUM_5, dio.getContact());
			ps.setString(NUM_6, dio.getContactnumber());
			ps.setString(NUM_7, dio.getCabinetnumber());
			ps.setString(NUM_8, dio.getCabinetposition());
			ps.setString(NUM_9, dio.getHandler());
			ps.setDate(NUM_10, dio.getHandletime());
			if (null != dio.getRemarks() && !dio.getRemarks().trim().equals(""))
			{
				ps.setString(NUM_11, dio.getRemarks());
			}
			ps.setString(NUM_12, dio.getDevicestate());
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.fatal("DeviceIORegisterDBAcess.modifyDeviceRecord.failure", e);
			throw e;
		}
		finally
		{
			closeDao(conn, ps, null);
		}
	}

	@Override
	public void addDeviceRecord(DeviceIO dio) throws Exception
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = getConnection();
			ps = conn.prepareStatement(sql.getSQL("addDeviceRecord"));
			ps.setString(NUM_1, dio.getId());
			ps.setString(NUM_2, dio.getDevicemodel());
			ps.setString(NUM_3, dio.getDeviceindex());
			ps.setString(NUM_4, dio.getDepartment());
			ps.setString(NUM_5, dio.getContact());
			ps.setString(NUM_6, dio.getContactnumber());
			ps.setString(NUM_7, dio.getCabinetnumber());
			ps.setString(NUM_8, dio.getCabinetposition());
			ps.setString(NUM_9, dio.getHandler());
			ps.setDate(NUM_10, dio.getHandletime());
			if (null != dio.getRemarks() && !dio.getRemarks().trim().equals(""))
			{
				ps.setString(NUM_11, dio.getRemarks());
			}
			ps.setString(NUM_12, dio.getDevicestate());
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.fatal("DeviceIORegisterDBAcess.addDeviceRecord.failure", e);
			throw e;
		}
		finally
		{
			closeDao(conn, ps, null);
		}
	}

	@Override
	public DeviceIO findDeviceRecord(String id) throws Exception
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DeviceIO dio = null;
		try
		{
			conn = getConnection();
			ps = conn.prepareStatement(sql.getSQL("findDeviceRecord"));
			ps.setString(Constants.NUM_1, id);
			rs = ps.executeQuery();
			if (null != rs && rs.next())
			{
				dio = new DeviceIO();
				dio.setId(rs.getString("id"));
				dio.setDevicemodel(rs.getString("devicemodel"));
				dio.setDeviceindex(rs.getString("deviceindex"));
				dio.setDepartment(rs.getString("department"));
				dio.setContact(rs.getString("contact"));
				dio.setContactnumber(rs.getString("contactnumber"));
				dio.setCabinetnumber(rs.getString("cabinetnumber"));
				dio.setCabinetposition(rs.getString("cabinetposition"));
				dio.setHandler(rs.getString("handler"));
				dio.setHandletime(rs.getDate("handlertime"));
				if (null != rs.getString("remarks") && !rs.getString("remarks").trim().equals(""))
				{
					dio.setRemarks(rs.getString("remarks"));
				}
				dio.setDevicestate(rs.getString("devicestate"));
			}
		}
		catch (Exception e)
		{
			logger.fatal("DeviceIORegisterDBAcess.findDeviceRecord.failure", e);
			throw e;
		}
		finally
		{
			closeDao(conn, ps, rs);
		}
		return dio;
	}

	@Override
	public List<DeviceIO> searchDeviceRecords(SearchParameters pars) throws Exception
	{
		List<DeviceIO> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DeviceIO dio = null;
		try
		{
			conn = getConnection();
			StringBuffer buff = new StringBuffer();
			buff
					.append("SELECT * FROM (SELECT *,row_number()OVER(ORDER BY handlertime) AS rn FROM DeviceIORegisterTab WHERE 1=1 ");
			buff.append(pars.getParametersSQL());
			buff.append(" ) AS d ");
			buff.append(" WHERE d.rn BETWEEN ");
			buff.append(pars.getStartNumber());
			buff.append(" AND ");
			buff.append(pars.getEndNumber());
			logger.info("AccountNumberDBAcess.query sql = " + buff.toString());
			ps = conn.prepareStatement(buff.toString());
			rs = ps.executeQuery();
			if (null != rs)
			{
				list = new LinkedList<DeviceIO>();
				while (rs.next())
				{
					dio = new DeviceIO();
					dio.setId(rs.getString("id"));
					dio.setDevicemodel(rs.getString("devicemodel"));
					dio.setDeviceindex(rs.getString("deviceindex"));
					dio.setDepartment(rs.getString("department"));
					dio.setContact(rs.getString("contact"));
					dio.setContactnumber(rs.getString("contactnumber"));
					dio.setCabinetnumber(rs.getString("cabinetnumber"));
					dio.setCabinetposition(rs.getString("cabinetposition"));
					dio.setHandler(rs.getString("handler"));
					dio.setHandletime(rs.getDate("handlertime"));
					if (null != rs.getString("remarks")
							&& !rs.getString("remarks").trim().equals(""))
					{
						dio.setRemarks(rs.getString("remarks"));
					}
					dio.setDevicestate(rs.getString("devicestate"));
					list.add(dio);
				}
			}
		}
		catch (SQLException e)
		{
			logger.fatal("DeviceIORegisterDBAcess.searchDeviceRecords.failure", e);
			throw e;
		}
		finally
		{
			closeDao(conn, ps, rs);
		}
		return list;
	}

	@Override
	public int getMaxPage(int pageSize) throws Exception
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = getConnection();
			ps = conn.prepareStatement(sql.getSQL("getMaxPage"));
			rs = ps.executeQuery();
			if (null != rs && rs.next())
			{
				return rs.getInt("count") / pageSize + 1;
			}
			else return 0;
		}
		catch (Exception e)
		{
			logger.fatal("DeviceIORegisterDBAcess.findDeviceRecord.failure", e);
			throw e;
		}
		finally
		{
			closeDao(conn, ps, rs);
		}
	}

	public static void closeDao(Connection conn, PreparedStatement ps, ResultSet rs)
	{
		try
		{
			if (null != conn)
			{
				conn.close();
			}
			if (null != ps)
			{
				ps.close();
			}
			if (null != rs)
			{
				rs.close();
			}
		}
		catch (Exception e)
		{
			conn = null;
			ps = null;
			rs = null;
		}
	}

}
