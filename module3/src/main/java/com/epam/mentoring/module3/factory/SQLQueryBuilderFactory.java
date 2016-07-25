package com.epam.module2.factory;

import com.epam.module2.builder.MssqlSqlQueryBuilder;
import com.epam.module2.builder.MysqlSqlQueryBuilder;
import com.epam.module2.builder.OracleSqlQueryBuilder;
import com.epam.module2.builder.SqlQueryBuilder;

/**
 * @author Siarhei_Karytka
 */
public class SQLQueryBuilderFactory
{
	public enum DB
	{
		MYSQL,
		MSSQL,
		ORACLE
	}

	public static SqlQueryBuilder createSqlQueryBuilder(DB dbProvider) throws Exception
	{
		switch (dbProvider)
		{
			case MSSQL:
				return new MssqlSqlQueryBuilder();
			case MYSQL:
				return new MysqlSqlQueryBuilder();
			case ORACLE:
				return new OracleSqlQueryBuilder();
			default:
				throw new Exception("Unsupported DB provider");
		}
	}
}
