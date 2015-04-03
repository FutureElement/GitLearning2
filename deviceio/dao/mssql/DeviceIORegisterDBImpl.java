package com.jbns.asset.deviceio.dao.mssql;

import com.jbns.asset.dao.common.AccountNumberDBAcess;

public class DeviceIORegisterDBImpl extends AccountNumberDBAcess
{

	public DeviceIORegisterDBImpl(String product) throws Exception
	{
		super(MSSQL_PRODUCT);
	}

}
